export default [{
  path: '',
  redirect: 'notice'
}, {
  path: 'notice',
  name: 'apps-dictation-notice',
  component: () => import(/* webpackChunkName: "apps-dictation-notice" */ '@/apps/dictation/pages/NoticePage.vue')
}, {
  path: 'dictation',
  name: 'apps-dictation-dictation',
  component: () => import(/* webpackChunkName: "apps-dictation-dictation" */ '@/apps/dictation/pages/DictationPage.vue')
}, {
  path: 'stats',
  name: 'apps-dictation-stats',
  component: () => import(/* webpackChunkName: "apps-dictation-stats" */ '@/apps/dictation/pages/StatsPage.vue')
}, {
  path: 'apply',
  name: 'apps-dictation-apply',
  component: () => import(/* webpackChunkName: "apps-dictation-apply" */ '@/apps/dictation/pages/ApplyPage.vue')
}, {
  path: 'qna',
  name: 'apps-dictation-qna',
  component: () => import(/* webpackChunkName: "apps-dictation-qna" */ '@/apps/dictation/pages/QnaPage.vue')
}, {
  path: 'modify',
  name: 'apps-dictation-modify',
  component: () => import(/* webpackChunkName: "apps-dictation-modify" */ '@/apps/dictation/pages/ModifyPage.vue')
}]
