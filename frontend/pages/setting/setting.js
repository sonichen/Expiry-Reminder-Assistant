// pages/setting/setting.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        username:'',
        value:'请选择性别',
        sexList:['男','女','保密'],
        hideFlag: true,
        jointime:'2022.5.1',
        phonenumber:'',
        mima:'*******',
        email:''
    },

 //是否
 issure() {
    wx.showModal({
        title: '提示',
        content: '是否确认保存',
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
                 }
               })
                                                        
            }else{
               console.log('用户点击取消')
            }

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
    // 显示遮罩层2--------------------------------------------------
  showModal: function () {
    var that = this;
    that.setData({
      hideFlag: false
    })
    // 创建动画实例
    var animation = wx.createAnimation({
      duration: 400,//动画的持续时间
      timingFunction: 'ease',//动画的效果 默认值是linear->匀速，ease->动画以低速开始，然后加快，在结束前变慢
    })
    this.animation = animation; //将animation变量赋值给当前动画
    var time1 = setTimeout(function () {
      that.slideIn();//调用动画--滑入
      clearTimeout(time1);
      time1 = null;
    }, 100)
  },
  //取消
mCancel: function () {
    var that = this;
    that.hideModal();
  },
// 隐藏遮罩层
hideModal: function () {
var that = this;
var animation = wx.createAnimation({
  duration: 400,//动画的持续时间 默认400ms
  timingFunction: 'ease',//动画的效果 默认值是linear
})
this.animation = animation
that.slideDown();//调用动画--滑出
var time1 = setTimeout(function () {
  that.setData({
    hideFlag: true,
  })
  clearTimeout(time1);
  time1 = null;
}, 220)//先执行下滑动画，再隐藏模块

},
//动画 -- 滑入
slideIn: function () {
this.animation.translateY(0).step() // 在y轴偏移，然后用step()完成一个动画
this.setData({
  //动画实例的export方法导出动画数据传递给组件的animation属性
  animationData: this.animation.export()
})
},
//动画 -- 滑出
slideDown: function () {
this.animation.translateY(300).step()
this.setData({
  animationData: this.animation.export(),
})
},  
// 点击选项
getOption:function(e){
    var that = this;
    that.setData({
       value:e.currentTarget.dataset.value,
       hideFlag: true
    })
  },



  gotomy(){
      wx.switchTab({
        url: '/pages/my/my',
      })
  }
})