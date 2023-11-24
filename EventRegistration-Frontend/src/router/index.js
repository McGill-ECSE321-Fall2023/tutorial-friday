import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Users from '@/components/Users'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/hello',
      name: 'Hello',
      component: Hello,
      beforeEnter: (from, to, next) => {
        alert("Are you sure you're allowed to access this page?");
        next();
        // Or call next(false) to abort the navigation. See the docs at
        // https://v3.router.vuejs.org/guide/advanced/navigation-guards.html
      }
    },
    {
      path: '/',
      name: 'Users',
      component: Users
    }
  ]
})
