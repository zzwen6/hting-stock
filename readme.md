## 聚合数据(接口限制，一天只能免费1000次请求)


### 1. 沪深股市


### 2. 香港股市


### 3. 美国股市


### 4. 香港股市列表


### 5. 美国股市列表


以下两个用来查询股票有哪些

### 6. 深圳股市列表 ****


### 7. 沪股列表  ****
 

## 腾讯股票日行情接口(不限制接口次数)

http://sqt.gtimg.cn/q=sh600519

返回一个js 变量字符串，要进行特殊处理
http://sqt.gtimg.cn/q=sh600519

说明可参考： https://blog.csdn.net/calmreason/article/details/94536985



### 新浪接口


### 获取当日股票基本数据
    http://hq.sinajs.cn/list=sh601006
    
    返回结果：JSON实时数据，以逗号隔开相关数据，数据依次是“股票名称、今日开盘价、昨日收盘价、当前价格、今日最高价、今日最低价、竞买价、竞卖价、成交股数、成交金额、买1手、买1报价、买2手、买2报价、…、买5报价、…、卖5报价、日期、时间”。

### 获取各个时间段行情图。
查看日K线图：http://image.sinajs.cn/newchart/daily/n/sh601006.gif
分时线的查询：http://image.sinajs.cn/newchart/min/n/sh000001.gif
日K线查询：http://image.sinajs.cn/newchart/daily/n/sh000001.gif
周K线查询：http://image.sinajs.cn/newchart/weekly/n/sh000001.gif
月K线查询：http://image.sinajs.cn/newchart/monthly/n/sh000001.gif


### 获取历史数据点

http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=[市场][股票代码]&scale=[周期]&ma=no&datalen=[长度]

返回结果：获取5、10、30、60分钟JSON数据；day日期、open开盘价、high最高价、low最低价、close收盘价、volume成交量；向前复权的数据。

http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sz002095&scale=60&ma=no&datalen=1023

获取深圳市场002095股票的60分钟数据，获取最近的1023个节点。



### 网易接口 csv 文件

上海股票数据查询（浪潮）

http://quotes.money.163.com/service/chddata.html?code=0600756&start=20160902&end=20171108&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP


### 获取实时资金数据

http://qt.gtimg.cn/q=ff_sh600519

 0: 股票代码
  1: 主力流入
  2: 主力流出
  3: 主力净流入
  4: 主力净流入/资金流入流出总和
  5: 散户流入
  6: 散户流出
  7: 散户净流入
  8: 散户净流入/资金流入流出总和
  9: 资金流入流出总和1+2+5+6
 10: 未知
 11: 未知
 12: 名字
 13: 日期

### 获取盘口分析
http://qt.gtimg.cn/q=s_pksh600519

 0: 买盘大单
  1: 买盘小单
  2: 卖盘大单
  3: 卖盘小单


