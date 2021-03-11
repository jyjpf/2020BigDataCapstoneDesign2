import dictationRoutes from '../apps/dictation/routes'

export default [{
  path: '/apps/lecture/:lecture',
  component: () => import(/* webpackChunkName: "apps-dictation" */ '@/apps/dictation/DictationApp.vue'),
  children: [
    ...dictationRoutes
  ]
}]
