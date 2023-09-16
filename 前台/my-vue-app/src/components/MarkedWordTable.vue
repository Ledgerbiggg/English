<template>
  <div>
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
            :width="getValue(item)==='汉语解释' ? '350px' : '120px'">
        </el-table-column>
      </el-table>
    </div>
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
        {del: "取消搜藏"},
      ],
      tableData: [],
      explanationList: [],
      explanationVisible: false,
      count: 30,
      type:['所有单词'],
    }
  },
  mounted() {
    this.getTableData(this.count, this.type)
  },
  watch: {
    '$store.state.search'(newVal) {
      if (newVal === false || this.$store.state.tabIndex!==2) return
      let count = this.$store.state.count;
      let type = this.$store.state.type;
      this.count = count;
      this.type = type;
      this.getTableData(this.count, this.type)
      this.$store.commit('search', false);
    },
    '$store.state.tableData'(newVal) {
      if (!newVal) {
        this.getCacheData()
        return
      }
      // this.tableData=newVal
      let res = {}
      res.data = {}
      res.data.data = newVal
      res.data.data['tableBody'] = []
      res.data.data['tableBody'][0] = newVal
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
      console.log("row111, column, cell, event", row, column, cell, event)
      if (column['label'] === "取消搜藏") {
        this.$alert(`确定取消搜藏单词${row["word"]}吗？`, '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(res => {
          http.delete("/bookmarkedWord/deleteOneBookmarkedWord", {id: row['id']}).then(res => {
            this.explanationList.splice(row['uid'] - 1, 1)
            this.getTableData(this.count, this.type)
          });
        }).catch(res => {
          this.$message.info("取消删除")
        })
      } else if (column['label'] === "汉语解释") {
        let uid = row['uid'] - 1
        console.log("点击汉语解释",this.explanationList[uid])
        this.tableData[uid]['explanation'] = this.tableData[uid]['explanation'] === '显示' ? this.explanationList[uid] : '显示';
      }
    },
    handleData(res) {
        this.explanationList.splice(0, this.explanationList.length)
        for (let i = 0; i < res.data.data.length; i++) {
          let datumElement = res.data.data[i];
          this.explanationList.push(datumElement['explanation'])
          datumElement['uid'] = i + 1
          datumElement['del'] = "取消"
          datumElement['explanation'] = "显示"
        }
        this.tableData = res.data.data
    },

    getTableData(size, type) {
      console.log("type", type)
      type = type.join(",");
      http.get("/bookmarkedWord/getAllBookmarkedWord", {size: size, type}).then(res => {
        console.log("getAllBookmarkedWord", res.data.data)
        this.handleData(res, true)
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


.el-table__row .el-table_2_column_10 {
  color: #138500!important;
  font-weight: 600;
  cursor: pointer;
}

.el-table__row .el-table_2_column_11 {
  color: #ffd400!important;
  font-weight: 700;
  cursor: pointer;
}

.el-table__cell {
  text-align: center !important;
}

.el-table_2_column_9{
  font-weight: 600;
}
.el-table .warning-row .el-table_2_column_9 {
  background: rgba(196, 11, 11, 0.3);
}

.el-table .warning-mid .el-table_2_column_9 {
  background: rgba(210, 51, 189, 0.3);
}

.el-table .warning-low .el-table_2_column_9 {
  background: rgba(84, 199, 8, 0.3);
}

.el-table .one-row .el-table_2_column_9 {
  background: rgba(215, 229, 13, 0.3);
}

.el-table .zero-row .el-table_2_column_9 {
  background: rgba(8, 199, 142, 0.3);
}

.el-table .baby-low .el-table_2_column_9 {
  background: rgba(210, 186, 186, 0.3);
}

.el-table .baby-easy .el-table_2_column_9 {
  background: rgba(8, 199, 154, 0.31);
}

.el-table .baby-diff .el-table_2_column_9 {
  background: rgba(182, 8, 201, 0.3);
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

.el-tab-pane {
  height: 90vh;
  overflow-y: auto;
}
</style>