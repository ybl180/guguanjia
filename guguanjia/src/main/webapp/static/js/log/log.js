new Vue({
    el: "#main-container",
    data: {
        pageInfo: '',
        params: {}
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: "manager/syslog/toList",
                method:"post",
                data: this.params
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(function (error) {
                console.log(error)
            })
        },

    },
    created: function () {
        this.selectAll(1, 3);
    }
});