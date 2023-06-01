// pages/ownclass/ownclass.js
let name=''
let token= wx.getStorageSync('token');
Page({

    /**
     * 页面的初始数据
     */
    data: {
        tempFilePaths:'http://8.130.8.164:8080/img/re/resource/photo.png',
        sourceType: ['camera', 'album'],

        list:[]
    },
    getCategoriesName(event){
        name=event.detail.value
    },


    // 请求接口，新增
    add(){
        wx.request({
            url: 'http://re.vipgz1.91tunnel.com/categories/add', 
            method:'POST',
            data:{
                name:name
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
    /** 点击保存时跳转到管理页面 */
    gotomnm() {
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
                        url: '/pages/management/management',
                          })
                        }, 1000);
                     }
                   })
                                                            
                }else{
                   console.log('用户点击取消')
                }
 
            }
        }),
        this.add()
       
    },
    
   
    


    
     //头像点击处理事件，使用wx.showActionSheet()调用菜单栏
     buttonclick: function () {
        const that = this
        wx.showActionSheet({
          itemList: ['拍照', '相册'],
          itemColor: '',
          //成功时回调
          success: function (res) {
            if (!res.cancel) {
              /*
               res.tapIndex返回用户点击的按钮序号，从上到下的顺序，从0开始
               比如用户点击本例中的拍照就返回0，相册就返回1
               我们res.tapIndex的值传给chooseImage()
              */
              that.chooseImage(res.tapIndex)
            }
          },
          //失败时回调
          fail: function (res) {
            console.log('调用失败')
           },
          complete: function (res) { },
        })
      },
      
    /*
    判断storage是否缓存图片，如果是就将图片从storage取出并赋值给tempFilePaths
    否则就使用默认的图片
    */
    setHeader(){
      const tempFilePaths = wx.getStorageSync('tempFilePaths');
      if (tempFilePaths) {
        this.setData({
          tempFilePaths: tempFilePaths
        })
      } else {
        this.setData({
          tempFilePaths: '/assets/images/header.png'
        })
      }
    },
    
      chooseImage(tapIndex) {
        const checkeddata = true
        const that = this
        wx.chooseImage({
        //count表示一次可以选择多少照片
          count: 1,
          //sizeType所选的图片的尺寸，original原图，compressed压缩图
          sizeType: ['original', 'compressed'],
          //如果sourceType为camera则调用摄像头，为album时调用相册
          sourceType: [that.data.sourceType[tapIndex]],
          success(res) {
            // tempFilePath可以作为img标签的src属性显示图片
            const tempFilePaths = res.tempFilePaths
            //将选择到的图片缓存到本地storage中
            wx.setStorageSync('tempFilePaths', tempFilePaths)
            /*
            由于在我们选择图片后图片只是保存到storage中，所以我们需要调用一次   	        setHeader()方法来使页面上的头像更新
            */
            that.setHeader();
            wx.showToast({
              title: '设置成功',
              icon: 'success',
              duration: 2000
            })
          }
        })
      },
})