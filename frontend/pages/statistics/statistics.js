import * as echarts from '../components/ec-canvas/echarts';
 
let token= wx.getStorageSync('token');
var dataList = [];
var XValue = [];
var Chart = null;
var pieData= [{
          value: 55,
          name: '食品'
        }, {
          value: 20,
          name: '约会'
        }, {
          value: 1,
          name: '生日'
        }, {
          value: 20,
          name: '工作'
        }, {
          value: 38,
          name: '上课'
        }]
Page({
	/**
   * 页面的初始数据
   */
  data: {
    ec: {
      lazyLoad: true // 延迟加载
    }
  },
  getLocalCity() {
    return new Promise(resolve => {
        wx.request({
            url: 'http://re.vipgz1.91tunnel.com/analysis/pie',
          method:'POST',
          header: {
            'content-type': 'application/json' ,// 默认值
            'Accept': 'application/json',
            'token': token
          },
            success: res => {
             
              for(var i=0;i<res.data.data.length;i++){
                dataList[i]=res.data.data[i].value;
                XValue[i]=res.data.data[i].name
                // pieData=res.data.data[i];
         
              }
              pieData=res.data.data;
              //  dataList = [1,2,4,21,1,2,1,2,3];
                console.log(res.data.data)
                return resolve();
            },
        })
    });
},
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getLocalCity().then(result => {
      this.echartsComponnet = this.selectComponent('#mychart');
      this.getData(); //获取数据
      this.getTableInfo();
  });
   
  },
  getData: function () {
  	/**
  	 * 此处的操作：
  	 * 获取数据json
  	 */
    //如果是第一次绘制
    if (!Chart){
      this.init_echarts(); //初始化图表
    }else{
      this.setOption(Chart); //更新数据
    }
  },
  //初始化图表
  init_echarts: function () {
    this.echartsComponnet.init((canvas, width, height) => {
      // 初始化图表
      Chart = echarts.init(canvas, null, {
        width: width,
        height: height
      });
      // Chart.setOption(this.getOption());
      this.setOption(Chart);
      // 注意这里一定要返回 chart 实例，否则会影响事件处理等
      return Chart;
    });
  },
  setOption: function (Chart) {
    // Chart.clear();  // 清除
    Chart.setOption(this.getOption());  //获取新数据
  },
  getOption: function () {
    
    // 指定图表的配置项和数据
    var option = {
      title: {
        text: '数据分析',
        left: 'center'
      },
      backgroundColor: "#f1fffd",
    color: ["#37A2DA", "#32C5E9", "#67E0E3", "#91F2DE", "#FFDB5C", "#FF9F7F","#2e2e2e","#7cb342"],
     
      tooltip: {
        show: true,
        trigger: 'axis'
      },
      series: [{
        label: {
          normal: {
            fontSize: 14
          }
        },
        type: 'pie',
        center: ['50%', '50%'],
        radius: '60%',//['40%', '60%'],
        itemStyle: {
          normal: {
            label: {
              show: true,
              //position: 'inner',
              formatter: function(params) {
                return (params.percent - 0).toFixed(0) + '%'
              }
            },
            labelLine: {
              show: false
            }
          },
          emphasis: {
            label: {
              show: true,
              formatter: "{b}\n{d}%"
            }
          }
        },
        data:pieData
      }]
    }
    return option;
  },
    //获取表格信息
    getTableInfo(){
      var that=this;
        wx.request({
            url: 'http://re.vipgz1.91tunnel.com/analysis/analysis', 
            method:'POST',
            header: {
              'content-type': 'application/json' ,// 默认值
              'Accept': 'application/json',
              'token': token
            },
            success (res) {    
              if(res.data.code==200){
                 that.setData({
                   list:res.data.data
               })
              }
            }
          })
    },

})
