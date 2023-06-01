// pages/exdata/exdata.js
let token= wx.getStorageSync('token');
let  selectData='',Id=''
Page({

    /**
     * 页面的初始数据
     */
    data: {

        show:false,//控制下拉列表的显示隐藏，false隐藏、true显示
        selectData:[],//下拉列表的数据
      
        id:0,
        value:[]
    },

    //请求接口，显示所有类别
 queryAll(){
    var that=this;
      wx.request({
          url: 'http://re.vipgz1.91tunnel.com/categories/queryAll', 
          method:'POST',
          header: {
            'content-type': 'application/json' ,// 默认值
            'Accept': 'application/json',
            'token': token
          },
          success (res) {
            that.setData({
                selectData:res.data.data
            })
            selectData=res.data.data
            console.log((selectData))
            // console.log((list))
          }
        })
  },

  // 点击下拉显示框
  selectTap(){
    this.setData({
     show: !this.data.show
    });
    },
    // 点击下拉列表
    optionTap(e){
   
    Id=e.currentTarget.dataset.id;
    console.log(e.currentTarget)
    this.setData({
     id:Id,
     value:e.currentTarget.dataset.value,
     show:!this.data.show
    });
    },

    //是否导出
    ifex() {
        wx.showModal({
            title: '提示',
            content: '是否确认导出，数据将发送至您的邮箱',
            success: function (res) {
                if (res.confirm) {
                    console.log('用户点击确定')
                    wx.showToast({
                        title: '成功',
                        duration: 1000,
                        success: function () {
                        setTimeout(function () {
                        wx.reLaunch({
                        url: '/pages/my/my',
                          })
                        }, 1000);
console.log(Id),
                        wx.request({
                            url: 'http://re.vipgz1.91tunnel.com/send/send?categoriesId='+Id,
                            method:'POST',
                            data:{
                                categoriesId:Id
                            },
                            header: {   
                              'content-type': 'application/json' ,// 默认值
                              'Accept': 'application/json',
                              'token': token
                            },
                            success (res) {
                              console.log(res)
                            }
                          })
                     }
                   })
                                                            
                }else{
                   console.log('用户点击取消')
                }
 
            }
        })
    },

    onLoad: function (options) {
    
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
       this.queryAll()
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

    }
})