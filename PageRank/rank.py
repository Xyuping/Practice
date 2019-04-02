#!/usr/bin/env python
# -*- coding: utf-8 -*-

import numpy as np
import  csdn.spiders.database as db


'''
从 ulinks 表获取 G
{
'url1':{'url_a':1,'url_b:1...},----(前面的链接指向后面)
'url2':{'url_b':1,'url_c:1...},
'url3':{'url_c':1,'url_e:1...},
...
'urln':{'url_z':1,
}
'''
def getG(conn,cursor):
    cursor.execute('SELECT url FROM urlpr')
    urls = cursor.fetchall()
    results={}
    for item in urls:
        url=item[0]
        cursor.execute('SELECT links FROM ulinks WHERE url=%s',url)
        value={}
        for link in cursor.fetchall():
            value[link[0]]=1
        results[url]=value
    return results

'''
Generate_Transfer_Matrix:产生概率转移矩阵
参数:页面链接矩阵
{
'url1':{'url_a':1,'url_b:1...},----(前面的链接指向后面)
'url2':{'url_b':1,'url_c:1...},
'url3':{'url_c':1,'url_e:1...},
...
'urln':{'url_z':1,
}

返回:转化矩阵,节点到索引的字典,索引到节点的字典(在PageRank 中会用到)
'''
def Generate_Transfer_Matrix(G):
    index_to_node = dict()
    node_to_index = dict()
    for index,node in enumerate(G.keys()):
        node_to_index[node] = index
        index_to_node[index] = node
    n = len(node_to_index)
    M = np.zeros([n,n])
    for node1 in G.keys():
        for node2 in G[node1]:
            try:
                M[node_to_index[node2],node_to_index[node1]] = 1/len(G[node1])
            except:
                continue
    for node1 in G.keys():
        flag = True
        for i in range(n):
            if M[i, node_to_index[node1]] != 0:
                flag = False
                break
        if flag:
            for i in range(n):
                M[i, node_to_index[node1]] = 1 / n
    return M, node_to_index, index_to_node

'''
PageRank:
参数:  M:url 的转化矩阵; alpha:点击链接跳转到页面的概率; root: 起始页面
返回:  排好序的元组列表[( url1,PR1),(url2,PR2)...]
'''
def PageRank(M, alpha, index_to_node):
    result = []
    n = len(M)
    v = np.zeros(n)
    v1 = np.zeros(n)
    for i in range(n):
        v[i]=1
        v1[i]=1/n
    while np.sum(abs(v - (alpha*np.matmul(M,v) + (1-alpha)*v1))) > 0.0001:
        v = alpha * np.matmul(M, v) + (1-alpha)*v1
    for ind, prob in enumerate(v):
        result.append((index_to_node[ind], prob))
    return result

'''
getV:
功能:
将{
'url1':{'time':value,'text':'value','pr':value},
'url2':{'time':value,'text':'value','pr':value},
'url3':{'time':value,'text':'value','pr':value},
...
'urln':{'time':value,'text':'value','pr':value},
}
转化成
{time1,time2,...timen}
{text1,text2,...,textn}----------此处 text 已经根据相关性转化成响应的值
{pr1,pr2,...,prn}
'''
def getV(urls):
    index_to_node = dict()
    node_to_index = dict()
    for index, node in enumerate(urls.keys()):  # keys : url1, url2...
        node_to_index[node] = index
        index_to_node[index] = node
    n = len(node_to_index)
    pr = np.zeros(n)
    text = np.zeros(n)
    time = np.zeros(n)
    for node1 in urls.keys():
        try:
            time[node_to_index[node1]]=urls[node1]['time']
            text[node_to_index[node1]]=urls[node1]['text']
            pr[node_to_index[node1]]=urls[node1]['pr']
        except:
            continue
    return time,text,pr,node_to_index,index_to_node

'''
rank:
根据各自权重( time,text,pr) 进行排序
'''
def rank(w_time,w_text,w_PR,urls):
    time,text,PR,node_to_index,index_to_node=getV(urls)
    result = []
    n = len(urls)
    v = np.zeros(n)
    for i in range(n):
        v[i]=1/n
    v = w_PR*PR+w_time*time+w_text*text;
    for ind, prob in enumerate(v):
        result.append((index_to_node[ind], float(prob),'new')) #(url,rank,status)
    return result

'''
getTTP:获得数据库中 time,text,pr, 返回类型:嵌套字典
'''
def getTTP(conn,cursor,PR_result):
    pr=dict(PR_result)
    cursor.execute('SELECT url,time,text FROM urlpr')
    urls = cursor.fetchall()  # 返回元组
    results = {}
    for item in urls:
        results[item[0]]={'time':item[1],'text':item[2],'pr':pr[item[0]]}
    return results
'''
返回状态为 new 且优先级最高的n 个链接作为下一轮爬虫链接
'''
def returnUrl(conn, cursor,n):
    cursor.execute('select url from queue where status=%s order by total_rank+0 desc limit 1', 'new')
    urls = cursor.fetchall()
    url_list = []
    if urls!=():
        for i in range(n):
            url_list.append(urls[i][0])
            cursor.execute('update queue set status=\'%s\' where url=\'%s\'' % ('done', urls[i][0]))
    return url_list

def getLinks(url,links):
    link_list=[]
    for link in links:
        item=(url,link)
        link_list.append(item)
    return link_list


def run(n,i,count,conn,cursor):
    flag = False
    url_que = returnUrl(conn, cursor, n)            #获取数据库中未被爬取过且优先级最高的页面链接
    if i==2**count or url_que==[]:                  #如果 i=2^count 或爬虫队列中所有链接均已被爬取，则进行排序
        G=getG(conn,cursor)
        M, node_to_index, index_to_node = Generate_Transfer_Matrix(G)
        alpha = 0.85  #点击链接进入页面的概率
        result = PageRank(M, alpha, index_to_node)  #计算 pr 值
        urls = getTTP(conn,cursor,result)           #得到各个页面对应的 time、text、pr 值
        result = rank(0.2, -0.1, 7000, urls)        #计算 rank 值
        db.updateQue(conn,cursor,result)               #更新爬虫队列
        if url_que == []:
            url_que = returnUrl(conn,cursor,n)      #如果排序前未获取到链接，则再次获取数据库中未被爬取过且优先级最高的页面链接
        flag = True
    return url_que,flag

