// pages/management/management.js
let token= wx.getStorageSync('token');
Page({

    /**
     * 页面的初始数据
     */
    data: {
        options:[
      
        ] ,//选项列表
        list:[],
        Id:[]
    },
     /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.getmycate(),
        this.queryAll()
     

    },
   
  
    gotoownclass(){
        wx.navigateTo({
          url: '/pages/ownclass/ownclass',
        })
    },
    
    

  

     // 请求接口，请求信息
     queryAll(){
        var that=this;
          wx.request({
              url: 'http://re.vipgz1.91tunnel.com/categories/queryCustom', 
              method:'POST',
              header: {
                'content-type': 'application/json' ,// 默认值
                'Accept': 'application/json',
                'token': token
              },
              success (res) {
                that.setData({
                  list:res.data.data
                })
                console.log((res.data.data))
                // console.log((list))
              }
            })
      },
   
  
// 获取id，请求接口，删除
   delOption(event){
    var  id= event.currentTarget.id;
   console.log(id)
    wx.request({
     url: 'http://re.vipgz1.91tunnel.com/categories/delete?id='+id, 
     method:'POST',
     header: {
       'content-type': 'application/json' ,// 默认值
       'Accept': 'application/json',
       'token': token
     },
     success (res) {
      //刷新列表
     }
    
   }),
   this.onLoad()
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

//查询自定义分类
getmycate(){
    let token= wx.getStorageSync('token');
    console.log("token="+token)
      wx.request({
          url: 'http://re.vipgz1.91tunnel.com/categories/queryCustom', 
          method:'POST',
          header: {
            'content-type': 'application/json' ,// 默认值
            'Accept': 'application/json',
            'token': token
          },
          success (res) {
            console.log(res.data)
          }
        })
  }
})