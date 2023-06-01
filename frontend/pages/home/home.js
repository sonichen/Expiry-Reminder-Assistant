// pages/home/home.js
let recordStartX = 0, currentOffsetX = 0, curIndex = 0; 
let state=0,keyword='';
let token= wx.getStorageSync('token');
Page({

    /**
     * 页面的初始数据
     */
    data: {
        logoFlag:true,//无物品记录时显示
        
        // name:'旺仔牛奶',
        itemName:'旺仔牛奶',
        timeGap:90,
       
        list:[{id:1}],

        show:false,//控制下拉列表的显示隐藏，false隐藏、true显示
        selectData:['未过期','已过期','临期过期'],//下拉列表的数据
        index:0,//选择的下拉列表下标

        state:0,

        qRCodeMsg:'',

        List:[{}],
        allthing:[]
    },
 //选择排序方式—————————————————————————————————————————————————————————
    // 点击下拉显示框
    selectTap(){
        this.setData({
         show: !this.data.show
        });
        },
        // 点击下拉列表
        optionTap(e){
        let Index=e.currentTarget.dataset.index;//获取点击的下拉列表的下标
        console.log("index="+Index)
        state=Index;
        this.queryByState();
        this.setData({
         index:Index,
         show:!this.data.show,
         state:state
        });
        },
        
        
     
    
        

//物品记录的数据渲染到页面中
 // 请求接口，请求信息（按过期时间）
 queryByState(){
    var that=this;
    let token= wx.getStorageSync('token');
      wx.request({
          url: 'http://re.vipgz1.91tunnel.com/record/queryAllByState?state='+state, 
          method:'POST',
          header: {
            'content-type': 'application/json' ,// 默认值
            'Accept': 'application/json',
            'token': token
          },
          success (res) {
            that.setData({
              List:res.data.data
            })
            console.log((res.data.data))
             //console.log(list)
          },
          
        })
  },
//如果数组为空，显示去添加的界面
if:function(){
    if(List.length==0){state=4}
},

//根据状态查询物品记录
GetThingRecord(event){
    let token= wx.getStorageSync('token');
    console.log("token="+token);
      wx.request({
          url: 'http://re.vipgz1.91tunnel.com/record/queryAllByState', 
          method:'POST',
          data:{
            state:0
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
  },
   
  //查询全部物品记录
  searchall(){
    var that=this;
    let token= wx.getStorageSync('token');
      wx.request({
          url: 'http://re.vipgz1.91tunnel.com/record/queryAllRecord',
          method:'POST',
          header: {
            'content-type': 'application/json' ,// 默认值
            'Accept': 'application/json',
            'token': token
          },
          success (res) {
            that.setData({
              allthing:res.data.data
            })
            console.log((res.data.data))
             //console.log(list)
          },
          
        })
  },
   
    //点击去添加跳转到创建页面
    gotoadd(){
        wx.switchTab({
          url: '/pages/create/create',
        })
    },

// 获取id，请求接口，删除
delOption(event){
    let token= wx.getStorageSync('token');
    var  id= event.currentTarget.id;
   console.log(id)
    wx.request({
     url: 'http://re.vipgz1.91tunnel.com/record/delete?id='+id, 
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

//请求接口，搜索关键字
SearchThingRecord(event){
    let token= wx.getStorageSync('token');
    console.log("token="+token);
    var  keyword= event.detail.value;
    console.log(event),
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
            console.log(res.data)
          }
        })
  },
/* 
//双击事件，双击修改界面（暂时不做，未完善）
DoubleClick: function(e){
   
        // 获取这次点击时间
        var thisTime = e.timeStamp;
        // 获取上次点击时间 默认为0
        var lastTime = this.data.lastTime;
        // 打印这次点击时间
        console.log("这次时间：" + thisTime)
        // 打印参数
        console.log("参数：" + e.target.dataset.name)
        if (lastTime != 0) {
          if (thisTime - this.data.lastTime < 500)
            console.log("双击事件")
        }
        // 赋值
        this.setData({
          lastTime: thisTime
        }),
        wx.navigateTo({
          url: '/pages/home-edit/home-edit',
        })
    
},
*/

//点击搜索栏进入搜索界面
ClickSearch:function(e){
    console.log(e),
    wx.navigateTo({
        url: '/pages/home-search/home-search',
      })
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
          console.log(qRCodeMsg);
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
    
requestcode(){

},

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad:function(options) {
        this.queryByState(),
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
    onLoad: function (options) {
        this.searchall();
        this.queryByState();
    }
    

})
   
