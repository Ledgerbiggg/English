<template>
  <div class="main">
    <div class="left">
      <Table></Table>
    </div>
    <div class="right">
      <Search></Search>
      <Select></Select>
      <Upload></Upload>
    </div>
  </div>
</template>

<script>
import Search from "@/components/Search";
import Upload from "@/components/Upload"
import Table from "@/components/Table";
import Select from "@/components/Select";

export default {
  name: "Main",
  components: {
    Search,
    Upload,
    Table,
    Select,
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
  overflow-y: auto;
}

.right {

}


</style>