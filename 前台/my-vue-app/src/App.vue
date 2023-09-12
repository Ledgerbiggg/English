<template>
  <div id="app">
    <router-link to="/main">
      <Main/>
    </router-link>
    <router-view></router-view>
  </div>
</template>

<script>
import Main from '@/view/Main.vue'; // 引入子组件

export default {
  name: 'App',
  components: {
    Main
  }
}
</script>

<style>
body{
  width: 100vw;
  height: 100vh;
  overflow: hidden;
}
*{
  padding: 0;
  left: 0;
}

::-webkit-scrollbar{
  display:none;
}
</style>
