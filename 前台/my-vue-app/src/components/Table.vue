<template>
  <div class="table">
    <el-table
        @header-click="handleClickHead"
        @cell-click="handleClickBody"
        :data="tableData"
        style="width: 100%"
        :row-class-name="tableRowClassName">
      <el-table-column
          v-for="(item,key) in tableHead" :key="key"
          :prop="getKey(item)"
          :label="getValue(item)"
          hight="85px"
          :class="getValue(item)==='删除'?'del':''"
          :width="getValue(item)==='汉语解释' ? '420px' : '150px'">
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
import http from "@/js/http";

export default {
  data() {
    return {
      tableHead: [
        {uid: "序号"},
        {sort: "分类"},
        {word: "单词"},
        {explanation: "汉语解释"},
        {del: "删除"},
      ],
      tableData: [],
      explanationList: [],
      explanationVisible: false,
      count: 30,
      type: '所有单词',
    }
  },
  mounted() {
    this.getCacheData()
  },
  watch: {
    '$store.state.search'(newVal) {
      if(newVal===false) return
      let count = this.$store.state.count;
      let type = this.$store.state.type;
      this.count = count;
      this.type = type;
      this.getTableData(this.count, this.type)
      this.$store.commit('search', false);
    },
    '$store.state.tableData'(newVal){
      if(!newVal){
        this.getCacheData()
        return
      }
      // this.tableData=newVal
      let res={}
      res.data={}
      res.data.data=newVal
      res.data.data['tableBody']=[]
      res.data.data['tableBody'][0]=newVal
      this.handleData(res);
    }
  },
  methods: {
    handleClickHead: function (column, event) {
      if (column['label'] !== '汉语解释') return
      this.$alert(`确定要${this.explanationVisible ? '隐藏' : '显示'}全部中文?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(res => {
        if (!this.explanationVisible) {
          for (let i = 0; i < this.tableData.length; i++) {
            let tableDatum = this.tableData[i];
            tableDatum.explanation = this.explanationList[i];
          }
        } else {
          for (let i = 0; i < this.tableData.length; i++) {
            let tableDatum = this.tableData[i];
            tableDatum.explanation = '显示';
          }
        }
        this.explanationVisible = !this.explanationVisible
      }).catch(res => {

      })
    },
    handleClickBody: function (row, column, cell, event) {
      console.log(row, column, cell, event)
      if (column['label'] === "删除") {
        this.$alert(`确定删除单词${row["word"]}吗？`, '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(res => {
          http.delete("/deleteOneItem", {id: row['id']}).then(res => {
            this.explanationList.splice(row['uid'] - 1, 1)
            this.getCacheData()
          });
        }).catch(res => {
          this.$message.info("取消删除")
        })
      } else if (column['label'] === "汉语解释") {
        let uid = row['uid']-1
        this.tableData[uid]['explanation']=this.tableData[uid]['explanation']==='显示'?this.explanationList[uid]:'显示';
      }
    },
    handleData(res) {
      if (res.data.data == null || res.data.data === '') {
        this.$alert("ops太久没有使用啦，要重新随机获取数据", "提示", {
          confirmButtonText: '重新获取', // 确认按钮文本
          cancelButtonText: "取消",
          type: 'warning',
        }).then(res => {
          this.getTableData(this.count,this.type)
        }).catch(res => {
          this.tableData = []
        })
      } else {
        this.explanationList.splice(0,this.explanationList.length)
        for (let i = 0; i < res.data.data["tableBody"].length; i++) {
          let datumElement = res.data.data["tableBody"][i];
          datumElement['uid'] = i + 1
          datumElement['del'] = "删除"
          this.explanationList.push(datumElement['explanation'])
          datumElement['explanation'] = "显示"
        }
        this.tableData = res.data.data["tableBody"]
      }
    },
    getCacheData() {
      http.get("/getCacheData").then((res) => {
        if (res.data.data.code !== 500) {
          this.handleData(res)
        } else {
          throw new Error('Code 500 encountered');
        }
      }).catch(res => {
        res.data = {}
        res.data.data = ""
        this.handleData(res)
      })
    },
    getTableData(size, type) {
      http.get("/getRandomData", {size: size, type: type}).then(res => {
        console.log("getRandomData",res.data.data)
        this.handleData(res,true)
        this.$message.success("数据获取成功")
      })
    },
    tableRowClassName({row, rowIndex}) {
      if (row['sort'] === '高频') {
        return 'warning-row';
      } else if (row['sort'] === '中频') {
        return 'warning-mid';
      } else if (row['sort'] === '低频') {
        return 'warning-low';
      } else if (row['sort'] === '1') {
        return 'one-row';
      } else if (row['sort'] === '真') {
        return 'zero-row';
      } else if (row['sort'] === '0') {
        return 'baby-low';
      } else if (row['sort'] === '简') {
        return 'baby-easy';
      } else if (row['sort'] === '超') {
        return 'baby-diff';
      }
      return '';
    },
    getValue(obj) {
      let keys = Object.keys(obj);
      return obj[keys[0]]
    },
    getKey(obj) {
      let keys = Object.keys(obj);
      return keys[0]
    }
  },

}
</script>

<style>
.el-table__row {
  height: 84px;
}

.el-table .el-table_1_column_1 {
  width: 30px !important;
}

.el-table .el-table_1_column_2 {
}

.el-table .el-table_1_column_3 {
  font-weight: 800;
}

.el-table__row .el-table_1_column_4 {
  color: #138500;
  font-weight: 600;
  cursor: pointer;
}

.el-table__row .el-table_1_column_5 {
  color: red;
  font-weight: 700;
  cursor: pointer;
}

.el-table__cell {
  text-align: center !important;
}

.el-table .warning-row .el-table_1_column_3{
  background: rgba(196, 11, 11, 0.3);
}

.el-table .warning-mid .el-table_1_column_3{
  background: rgba(210, 51, 189, 0.3);
}

.el-table .warning-low .el-table_1_column_3{
  background: rgba(84, 199, 8, 0.3);
}

.el-table .one-row .el-table_1_column_3{
  background: rgba(215, 229, 13, 0.3);
}

.el-table .zero-row .el-table_1_column_3{
  background: rgba(8, 199, 142, 0.3);
}

.el-table .baby-low .el-table_1_column_3{
  background: rgba(210, 186, 186, 0.3);
}

.el-table .baby-easy .el-table_1_column_3{
  background: rgba(8, 199, 154, 0.31);
}

.el-table .baby-diff .el-table_1_column_3{
  background: rgba(182, 8, 201, 0.3);
}
.el-table__row:hover{
}

.el-message-box__close {
  transform: translateX(190px);
}

.table {
  width: 130%;
  text-align: center;
  display: flex;
  justify-content: center;
}

</style>
