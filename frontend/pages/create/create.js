// pages/create/create.js
let description='',closeTime='',expireTime='',itemName='',categoriesId='',categoriesName='',qRCodeMsg='',codelist='';
let value2='请输入物品类别';
let token= wx.getStorageSync('token');
//获取应用实例
const app = getApp();
//引入插件：微信同声传译
const plugin = requirePlugin('WechatSI');
//获取全局唯一的语音识别管理器recordRecoManager
const manager = plugin.getRecordRecognitionManager();



Page({

    /**
     * 页面的初始数据
     */
    data: {
        value1:'请输入物品名称',
        optionList:['扫一扫自动添加','语音输入'],
        value2:'请选择物品类别',
       
        date2:'请选择物品生产日期',
        hideFlag1: true,//true-隐藏  false-显示
        hideFlag2: true,//true-隐藏  false-显示
        animationData: {},//
        content:'',
        namevoice:'',
       
        isrealinput:0,

        items: [{ name: '日期选择', value: 'date', checked: 'true' }, { name: '时间选择', value: 'time' }, { name: '日期时间选择', value: 'dateTime' }],
        mode: 'dateTime',

        List:[],
        classname:[],//所有类别名字

         //语音
        recordState: false, //录音状态
        content:'',//内容

        qRCodeMsg:'',//二维码,
        codelist:''
        
    },


    

    //扫描二维码
getQRCode: function(){
    // 允许从相机和相册扫码
    var _this = this;
    wx.scanCode({
      success:function(res) {
        console.log(res);
        console.log(res.result);
        qRCodeMsg=res.result;
        _this.setData({
            qRCodeMsg: res.result
          });
        wx.showToast({
          title: '成功',
          icon: 'success',
          duration: 2000
        }),
        _this.getGoodsInfo()
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

  //调用接口，获取二维码信息
  getGoodsInfo(){
    var that=this;
    wx.request({
        url: 'http://re.vipgz1.91tunnel.com/record/getGoodsInfo?code='+qRCodeMsg, 
        method:'POST',
        data:{
            code:qRCodeMsg
        },
        header: {   
          'content-type': 'application/json' ,// 默认值
          'Accept': 'application/json',
          'token': token
        },
        success (res) {
          itemName=res.data.data.goodsName;
          that.setData({
            itemName: res.data.data.goodsName,
            content:res.data.data.supplier+"--"+res.data.data.brand+"--"+res.data.data.standard
          });
        }
      })

  },

    //提醒时间，精确到秒
    onPickerChange1(e) {//返回回调函数
        console.log("onPickerChange", e),
        closeTime=e.detail.value
    },
    radioChange: function(e) {
        console.log(e)
        this.setData({
            mode: e.detail.value//获取类型；date年月日；time时分秒；dateTime年月日+时分秒
        })
    },

     //提醒时间，精确到秒
     onPickerChange2(e) {//返回回调函数
        console.log("onPickerChange", e),
        expireTime=e.detail.value
    },
    radioChange: function(e) {
        console.log(e)
        this.setData({
            mode: e.detail.value//获取类型；date年月日；time时分秒；dateTime年月日+时分秒
        })
    },
   

//物品名称回调函数
getThingName(e){
    console.log(e.detail.value)
    itemName=e.detail.value
},
//类别回调函数
getclass(e){
    console.log(e)
    //categoriesId=e.detail.value
    //categoriesName=
},
//备注回调函数
getdescription(e){
    console.log(e.detail.value)
    description=e.detail.value
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
              classname:res.data.data
            })
            console.log((res.data.data))
            // console.log((list))
          }
        })
  },


//物品名称下拉框
    // 显示遮罩层
    showModal1: function () {
        var that = this;
        that.setData({
          hideFlag1: false
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
    mCancel1: function () {
        var that = this;
        that.hideModal1();
      },
   // 隐藏遮罩层
   hideModal1: function () {
    var that = this;
    var animation = wx.createAnimation({
      duration: 400,//动画的持续时间 默认400ms
      timingFunction: 'ease',//动画的效果 默认值是linear
    })
    this.animation = animation
    that.slideDown();//调用动画--滑出
    var time1 = setTimeout(function () {
      that.setData({
        hideFlag1: true,
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
  getOption1:function(e){
    var that = this;
    that.setData({
      value:e.currentTarget.dataset.value,
      hideFlag1: true
    })
  },
//-------------------------------------------------------------------
//物品类别下拉框
// 显示遮罩层2
  showModal2: function () {
    var that = this;
    that.setData({
      hideFlag2: false
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
mCancel2: function () {
    var that = this;
    that.hideModal2();
  },
// 隐藏遮罩层
hideModal2: function () {
var that = this;
var animation = wx.createAnimation({
  duration: 400,//动画的持续时间 默认400ms
  timingFunction: 'ease',//动画的效果 默认值是linear
})
this.animation = animation
that.slideDown();//调用动画--滑出
var time1 = setTimeout(function () {
  that.setData({
    hideFlag2: true,
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
getOption2:function(e){
    var that = this;
    that.setData({
       value2:e.currentTarget.dataset.value,
       hideFlag2: true
    }),
    console.log(e.currentTarget.dataset.value),
    console.log(e.currentTarget.dataset.id),
    categoriesId=e.currentTarget.dataset.id,
    categoriesName=e.currentTarget.dataset.value

  },
//-------------------------------------------------------------------
//提醒时间
// 显示遮罩层3  时间
    bindDateChange1: function(e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
         date1: e.detail.value
        })
  },

/** 跳转到首页页面 */
jumpToDetail() {
     //获取用户授权
    wx.requestSubscribeMessage({
      tmplIds: ['CeAvOfonLLCKeyrXDEJbWnxv5yDA6pT5cx1eCGQzHsQ'],
      success(res) {
        if (res['CeAvOfonLLCKeyrXDEJbWnxv5yDA6pT5cx1eCGQzHsQ'] === 'accept') {
            console.log("授权成功",res),
            wx.showModal({
                title: '提示',
                content: '是否确认提交',
                success: function (res) {
                    if (res.confirm) {
                        console.log('用户点击确定')
                        wx.showToast({
                            title: '成功',
                            duration: 1000,
                            success: function () {
                            setTimeout(function () {
                            wx.reLaunch({
                            url: '/pages/home/home',
                              })
                            }, 1000);
                         }
                       })
                                                                
                    }else{
                       console.log('用户点击取消')
                    }
        
                }
            })
          // 用户主动点击同意...do something
        } else if (res['CeAvOfonLLCKeyrXDEJbWnxv5yDA6pT5cx1eCGQzHsQ'] === 'reject') {
            // 用户主动点击拒绝...do something
             wx.showModal({
            title: '提示',
            content: '你取消了订阅通知',
             showCancel: false
      })
          // 用户主动点击拒绝...do something
        } else {
          wx.showToast({
            title: '授权订阅消息有误',
            icon: 'none'
          })
        }
      },

      fail(res){
          console.log("授权失败",res),
          // 其他错误信息码，对应文档找出原因
          wx.showModal({
            title: '提示',
            content: res.errMsg,
            showCancel: false
      })
    }
    }),
    this.Create()
},

//点击临期提醒授权
shouquan:function(e){
   
    wx.requestSubscribeMessage({
      tmplIds: ['CeAvOfonLLCKeyrXDEJbWnxv5yDA6pT5cx1eCGQzHsQ'],
      success:(res)=>{
        if (res['CeAvOfonLLCKeyrXDEJbWnxv5yDA6pT5cx1eCGQzHsQ'] === 'accept') {
          console.log("授权成功",res),
          this.setData({
            isrealinput:1,
           
          })
      }else if (res['CeAvOfonLLCKeyrXDEJbWnxv5yDA6pT5cx1eCGQzHsQ'] === 'reject') {
        // 用户主动点击拒绝...do something
         wx.showModal({
        title: '提示',
        content: '您拒绝了提醒',
         showCancel: false,
         success: function (res) {
            if (res.confirm) {
              //点击确定按钮
              wx.switchTab({
                url: '/pages/home/home',
                  })
            }
        }
  })

      // 用户主动点击拒绝...do something
    }
    },
      fail(res){
          console.log("授权失败",res)
      }
    })

},
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //识别语音
    this.initRecord();
    this. queryAll()

    
  },
 // 手动输入内容（备注）
 conInput: function (e) {
    this.setData({
      content:e.detail.value,
    })
  },

  //识别语音 -- 初始化
  initRecord: function () {
    const that = this;
    // 有新的识别内容返回，则会调用此事件
    manager.onRecognize = function (res) {
      console.log(res)
    }
    // 正常开始录音识别时会调用此事件
    manager.onStart = function (res) {
      console.log("成功开始录音识别", res)
    }
    // 识别错误事件
    manager.onError = function (res) {
      console.error("error msg", res)
    }
    //识别结束事件（备注）
    manager.onStop = function (res) {
      console.log('..............结束录音')
      console.log('录音临时文件地址 -->' + res.tempFilePath); 
      console.log('录音总时长 -->' + res.duration + 'ms'); 
      console.log('文件大小 --> ' + res.fileSize + 'B');
      console.log('语音内容 --> ' + res.result);
      if (res.result == '') {
        wx.showModal({
          title: '提示',
          content: '听不清楚，请重新说一遍！',
          showCancel: false,
          success: function (res) {}
        })
        return;
      }
      var text = that.data.content + res.result;
      that.setData({
        content: text,
      })
    }
},


 
  //语音  --按住说话
  touchStart: function (e) {
    this.setData({
      recordState: true  //录音状态
    })
    // 语音开始识别
    manager.start({
      lang: 'zh_CN',// 识别的语言，目前支持zh_CN en_US zh_HK sichuanhua
    })
  },

 


  //语音  --松开结束
  touchEnd: function (e) {
    this.setData({
      recordState: false
    })
    // 语音结束识别
    manager.stop();
  },

   

  save(){
      wx.navigateBack({
        url: 'pages/create/create',
      })

  },

  

    gotovoice(){
        wx.navigateTo({
            url: '/pages/voice/voice',
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

    // 请求接口，新增
Create(){
    wx.request({
        url: 'http://re.vipgz1.91tunnel.com/record/add', 
        method:'POST',
        data:{
            categoriesId:categoriesId,          //分类id，下拉框选择的
            categoriesName:categoriesName,           //分类名称
            closeTime: closeTime,           //临期提醒 
            description:description,        //备注
            expireTime: expireTime,         //到期提醒 
            itemName: itemName            //名称
        },
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



  
