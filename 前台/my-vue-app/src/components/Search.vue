<template>
  <div class="search-box">
    <div>条件查询</div>
    <el-col :span="12">
      <div class="sub-title"></div>
      <el-autocomplete
          class="inline-input"
          v-model="state1"
          :fetch-suggestions="querySearch"
          placeholder="根据英文查询"
          @select="handleSelect"
      ></el-autocomplete>
    </el-col>
    <el-col :span="12">
      <div class="sub-title"></div>
      <el-autocomplete
          class="inline-input"
          v-model="state1"
          :fetch-suggestions="querySearch"
          placeholder="根据中文查询"
          @select="handleSelect"
      ></el-autocomplete>
    </el-col>
  </div>
</template>

<script>
export default {
  name:"Search",
  data() {
    return {
      restaurants: [],
      state1: '',
      state2: ''
    };
  },

  methods: {
    querySearch(queryString, cb) {
      let restaurants = this.restaurants;
      let results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    loadAll() {
      return [
        { "value": "南拳妈妈龙虾盖浇饭", "address": "普陀区金沙江路1699号鑫乐惠美食广场A13" }
      ];
    },
    handleSelect(item) {
      console.log(item);
    }
  },
  mounted() {
    this.restaurants = this.loadAll();
  }
}
</script>

<style scoped>
.search-box{
  text-align: center;
  margin-top: 50px;
  height: 100px;
}

</style>