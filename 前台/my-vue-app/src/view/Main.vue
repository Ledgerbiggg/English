<template>
  <div class="main">
    <div class="left">
      <Tab/>
    </div>
    <div class="right">
      <Search/>
      <Select/>
      <Upload/>
    </div>
  </div>
</template>

<script>
import Search from "@/components/Search";
import Upload from "@/components/Upload"
import Table from "@/components/Table";
import Select from "@/components/Select";
import Tab from "@/components/Tab.vue";

export default {
  name: "Main",
  components: {
    Search,
    Upload,
    Select,
    Tab,
  }
}
</script>

<style scoped>
.main {
  width: 90%;
  display: flex;
  align-content: center;
  justify-content: center;
  margin: 0 auto;
}

.left {
  width: 80%;
  height: 100vh;
  overflow: hidden;
  //background: #000;
}

.right {

}


</style>