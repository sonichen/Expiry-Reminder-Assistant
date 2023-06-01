// pages/my/my.js
let code='', nickname='',avatarurl='';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        avatarUrl:"http://8.130.8.164:8080/img/re/resource/avatar.png",//这里放了一张初始头像图片
        nickName:"点击获取头像和昵称",//用户昵称    
        userInfo:{},
        userInfoStr :''
    },


    //获取头像昵称
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
          //查看是否授权
          wx.getSetting({
            success: function(res) {
              if (res.authSetting['scope.userInfo']) {
                console.log("用户授权了");
              } else {
                //用户没有授权
                console.log("用户没有授权");
              }
            }
          });
          //获取code
          wx.login({
            success: function(res) {
              console.log(res.code);//这里的返回值里面便包含code
              code=res.code;
            },
            fail: function(res) {
              console.log('登陆失败');
            },
            complete: function(res) {},
          })

        
           
    },
   
    getUserProfile: function(res) {
      wx.getUserProfile({
        tmplIds: ['CeAvOfonLLCKeyrXDEJbWnxv5yDA6pT5cx1eCGQzHsQ'],
        desc: '用于微信账号与平台账号绑定', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
        success: (res)=>{
          console.log("获取到的用户信息成功: ",JSON.stringify(res));
          this.setData({
            userInfo: res,
            userInfoStr: JSON.stringify(res),
            nickName: res.userInfo.nickName,
            avatarUrl: res.userInfo.avatarUrl,
           
          }),
          console.log(res.userInfo.nickName),
          console.log(res.userInfo.avatarUrl),
          nickname=res.userInfo.nickName,
          avatarurl=res.userInfo.avatarUrl,
         
          wx.setStorage({    //数据缓存方法
            key: 'nickName',   //关键字，本地缓存中指定的key
            data: res.userInfo.nickName,    //缓存微信用户公开信息，
            success: function() {      //缓存成功后，输出提示
              console.log('写入nickName缓存成功')
            },
            fail: function() {        //缓存失败后的提示
              console.log('写入nickName发生错误')
            }
          }),
          wx.setStorage({    //数据缓存方法
            key: 'avatarUrl',   //关键字，本地缓存中指定的key
            data: res.userInfo.avatarUrl,    //缓存微信用户公开信息，
            success: function() {      //缓存成功后，输出提示
              console.log('写入avatarUrl缓存成功')
              
            },
            fail: function() {        //缓存失败后的提示
                console.log('写入avatarUrl发生错误')
              }
            }),
            
            //授权登录,code,头像，昵称
            wx.request({
                url: 'http://re.vipgz1.91tunnel.com:80/wxuser/wxLogin?code='+code+"&nickname="+nickname+"&avatarurl="+avatarurl,
                method:"GET",
                header: {
                    'content-type': 'application/json' // 默认值
                  },
                success:(e)=>{
                console.log(e)
                },
          })
        },

        fail: (res)=>{
          console.log("获取用户个人信息失败: ",res);
           //用户按了拒绝按钮
                 wx.showModal({
                    title: '警告',
                    content: '您点击了拒绝授权，将无法进入小程序，请授权之后再进入!!!',
                    showCancel: false,
                    confirmText: '返回授权',
                    success: function(res) {
                      // 用户没有授权成功，不需要改变 isHide 的值
                      if (res.confirm) {
                        console.log('用户点击了“返回授权”'); 
                      }
                    }
         });
        }
      })
    },
 
     






  
gotoguide(){
    wx.navigateTo({
      url: '/pages/my-guide/my-guide',
    })
},
gotoabus(){
    wx.navigateTo({
      url: '/pages/aboutus/aboutus',
    })
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

 /**
     * 点击进入设置
     */
    gotosetting(){
        wx.navigateTo({
            url: '/pages/setting/setting',
          })
    },
    
//点击进入反馈
    gotoadvice(){
        wx.navigateTo({
            url: '/pages/advice/advice',
          })
    },

//点击导出数据
gotoexdata(){
        wx.navigateTo({
            url: '/pages/exdata/exdata',
          })
    },



})