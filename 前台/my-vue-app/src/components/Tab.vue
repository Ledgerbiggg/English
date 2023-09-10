<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="单词词汇表" name="first">
        <Table/>
      </el-tab-pane>
      <el-tab-pane label="搜藏词汇表" name="second">
        <MarkedWordTable ref="MarkedWordTable"/>
      </el-tab-pane>
      <el-tab-pane label="时长统计" name="third">
       <EChartsView/>
      </el-tab-pane>
      <el-tab-pane label="定时任务补偿" name="fourth">定时任务补偿</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import Table from "@/components/Table.vue";
import MarkedWordTable from "@/components/MarkedWordTable.vue";
import EChartsView from "@/components/EChartsView.vue";
export default {
  name: "Tab",
  components: {
    Table,
    MarkedWordTable,
    EChartsView
  },
  data() {
    return {
      activeName: 'first',
    }
  },
  watch:{
    activeName(){
      console.log("activeName",this.activeName)
      if(this.activeName==='first'){
        console.log("first")
        this.$store.commit("changeTabIndex",1)
      }else if(this.activeName==='second'){
        this.$store.commit("changeTabIndex",2)
        this.$refs.MarkedWordTable.getTableData(999,['所有单词'])
      }
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    }
  }
}
</script>


<style scoped>

</style>