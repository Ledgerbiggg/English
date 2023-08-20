<template>
  <div id="app">
    <Components/>
  </div>
</template>

<script>
import Components from '@/view/Main.vue'; // 引入子组件

export default {
  name: 'App',
  components: {
    Components
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
