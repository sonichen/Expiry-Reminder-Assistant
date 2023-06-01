// pages/home-search/home-search.js
let keyword='';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        qRCodeMsg:'',
        Thing:[]
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    },

    //扫描二维码
getQRCode: function(){
    // 允许从相机和相册扫码
    var _this = this;
    wx.scanCode({
      success:function(res) {
        console.log(res);
        _this.setData({
            qRCodeMsg: res.result
          });
        wx.showToast({
          title: '成功',
          icon: 'success',
          duration: 2000
        })
      },
      fail: (res) =>{
        console.log(res);
        wx.showToast({
          title: '失败',
          icon: 'none',
          duration: 2000
        })
      }
    })
  },


//请求接口，搜索关键字
SearchThingRecord:function(event){
    var that=this;
    let token= wx.getStorageSync('token');
    console.log("token="+token);
    var  keyword= event.detail.value;
    console.log(keyword)
      wx.request({
          url: 'http://re.vipgz1.91tunnel.com/record/queryAllByKeyword?keyword='+keyword, 
          method:'POST',
          data:{
            keyword:keyword
        },
          header: {
            'content-type': 'application/json' ,// 默认值
            'Accept': 'application/json',
            'token': token
          },
          success (res) {
            
            that.setData({
                Thing:res.data.data
              }),
              console.log(res.data.data)
            
          }
        })
  }





})