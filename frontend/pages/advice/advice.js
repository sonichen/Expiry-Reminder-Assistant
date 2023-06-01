// pages/advice/advice.js
let content='',value='';
let token= wx.getStorageSync('token');
Page({

    /**
     * 页面的初始数据
     */
    data: {
       

    },
   

        /**
   * 输入框实时回调
   * @param {*} options 
   */
  remarkInputAction: function (options) {
    //获取输入框输入的内容
    value = options.detail.value;
    console.log("输入框输入的内容是 " + value)
  },


Addadvice(){
    wx.request({
        url: 'http://re.vipgz1.91tunnel.com/advice/add',
        method:'POST',
        data:{
            content:value
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
ifsend() {
        wx.showModal({
            title: '提示',
            content: '是否确认发送',
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
                        wx.request({
                            url: 'http://re.vipgz1.91tunnel.com/advice/add',
                            method:'POST',
                            data:{
                                content:value
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
})