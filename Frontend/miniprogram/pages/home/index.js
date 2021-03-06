//index.js
//获取应用实例
const app = getApp()

Page({
  options: {
    addGlobalClass: true,
  },
  data: {
    elements: [{
      title: '挂号',
      name: 'register',
      color: 'blue',
      icon: 'newsfill'
    },
    {
      title: '缴费',
      name: 'charge',
      color: 'cyan',
      icon: 'moneybagfill'
    }],
    isLogin: wx.getStorageSync('isLogin'),
    isBind: wx.getStorageSync('isBind'),
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    var that = this
    if (wx.getStorageSync('isLogin')){
      that.setData({
        isLogin: true
      });
    }
    if (wx.getStorageSync('isBind')) {
      that.setData({
        isBind: true
      });
    }
  },
  onShow: function () {
    var that = this
    if (wx.getStorageSync('isLogin')) {
      that.setData({
        isLogin: true
      });
    }
    if (wx.getStorageSync('isBind')) {
      that.setData({
        isBind: true
      });
    }
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})
