// store.js
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        count: 10,
        type:'所有单词',
        search:false,
        tableData:{}
    },
    mutations: {
        updateCount(state, newValue) {
            state.count = newValue
        },
        updateType(state, newValue){
            state.type = newValue
        },
        search(state, newValue){
            state.search = newValue
        },
        updateTableBySearch(state, newValue){
            state.tableData = newValue
        }
    }
})
