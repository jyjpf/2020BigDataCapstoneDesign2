export default {
  // main navigation - side menu
  menu: [{
    text: '',
    key: '',
    items: [
      { icon: 'mdi-view-dashboard-outline', text: '대시보드', link: '/dashboard/analytics' }
    ]
  }, {
    text: '학습',
    items: [
      { icon: 'mdi-circle-medium', text: '전체 수업', link: '/lecture' },
      { text: '내 수업 목록',
        items: [],
        disabled: false
      }
    ]
  }, {
    text: '내 정보',
    items: [{ icon: 'mdi-account-multiple-outline', text: '마이페이지', link: '/users/edit' }]
  }]
}