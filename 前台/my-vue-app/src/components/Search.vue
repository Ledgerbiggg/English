<template>
  <div class="search-box">
    <div class="search">条件查询</div>
    <el-col :span="12">
      <el-autocomplete
          class="inline-input"
          v-model="state1"
          :fetch-suggestions="querySearchWord"
          placeholder="使用英文查询"
          :trigger-on-focus="false"
          @select="handleSelect"
      ></el-autocomplete>
    </el-col>
    <el-col :span="12">
      <el-autocomplete
          class="inline-input"
          v-model="state2"
          :fetch-suggestions="querySearchExplanation"
          placeholder="使用中文查询"
          :trigger-on-focus="false"
          @select="handleSelect"
      ></el-autocomplete>
    </el-col>
  </div>
</template>

<script>
import http from "@/js/http";

export default {
  name: "Search",
  data() {
    return {
      words: [],
      explanations: [],
      state1: '',
      state2: ''
    };
  },

  methods: {
    querySearchWord(queryString, cb) {
      if (queryString) {
        http.get("/matchWordPrefix", {prefix: queryString}).then(res => {
          let data = res.data.data;
          if (data === null) {
            cb([{value:"查询不到结果",id:-1}])
          } else {
            data.forEach(i => {
              this.words.push({value: i['word'],id:i['id'],...i})
            })
            cb(this.words)
          }
        })
      }
      // 调用 callback 返回建议列表的数据
    },
    querySearchExplanation(queryString, cb) {
      if (queryString) {
        http.get("/matchCNPrefix", {keyword: queryString}).then(res => {
          let data = res.data.data;
          if (data === null) {
            cb([{value:"查询不到结果",id:-1}])
          } else {
            data.forEach(i => {
              this.explanations.push({value: i['explanation'],id:i['id'],...i})
            })
            cb(this.explanations)
          }

        })
      }
      // 调用 callback 返回建议列表的数据
    },
    handleSelect(item) {
      console.log('114474', item);
      if(item['value']!=='查询不到结果'){
        this.$store.commit('updateTableBySearch',item)
      }else {
        this.state1=''
        this.state2=''
      }
    }
  },
  watch:{
    state1(newVal){
      if(!newVal){
        this.$store.commit('updateTableBySearch','')
      }
    },
    state2(newVal){
      if(!newVal){
        this.$store.commit('updateTableBySearch','')
      }
    }
  }
}
</script>

<style scoped>
.search-box {
  text-align: center;
  margin-top: 50px;
  height: 100px;
}

.search {
  font-family: '华文楷体';
  font-weight: 600;
}

</style>