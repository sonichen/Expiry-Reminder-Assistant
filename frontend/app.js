// app.js

App({
    wxRequest(method, url, data, callback, errFun) { 
        let that = this;
        let token= wx.getStorageSync('token')?wx.getStorageSync('token'):'';
        wx.request({
          url: url,
          method: method,
          data: data,
          header: {
            'content-type': method == 'GET' ? 'application/json' : 'application/x-www-form-urlencoded',
            'Accept': 'application/json',
            'token': token
          },
          dataType: 'json',
          success: function (res) {
            callback(res.data);
          },
          fail: function (err) {
            console.log("请求失败：",err)
          }
        })
      },
   
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })


    wx.cloud.init({
        env:"study-6glw3r2vd05054dd"
      }),
      this.wxRequest('POST', 'http://re.vipgz1.91tunnel.com/categories/queryCustom', {}, (res) => {
        if (res.message == 401) { //返回401表示token不可用，则重新请求新的token
          //登录获取code请求后台
          wx.login({
            success: (res1) => {
              console.log(res1.code)
              if (res1.code) {
              //这里只是token过期，所以请求时不需要传用户信息，只要传code
                this.wxRequest('GET', 'http://re.vipgz1.91tunnel.com/wxuser/wxLogin?code='+res1.code, {
                }, (res2) => {
                  //缓存token
                  console.log(res2.token)
                  wx.setStorageSync('token', res2.token);
                  // console.log
                  return;
                })
              }
            }
          })
        }
      })
  },
  
  /**
   * 获取用户信息
   */
  handleGetUserInfo(e){
    const {userInfo} = e.detail;
    wx.setStorageSync("userInfo", userInfo);
    wx.navigateBack({
      delta: 1
    });
  },
  globalData: {
    userInfo: null
  }
  
	
     
  
  
})



