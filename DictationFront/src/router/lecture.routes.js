export default [{
  path: '/lecture',
  name: 'lecture-list',
  component: () => import(/* webpackChunkName: "lecture-list" */ '@/pages/lecture/LectureViewPage.vue')
}, {
  path: '/lecture/write',
  name: 'lecture-write',
  component: () => import(/* webpackChunkName: "lecture-list" */ '@/pages/lecture/LectureWrite.vue')
}]
