new Vue({
    el: "#fill-main-content",
    data: {
        pageInfo: {}
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            axios({
                url: "manager/demand/toIndex",
                params: {pageNum: pageNum, pageSize: pageSize}
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(error => {
                console.log(error)
            })
        },
        toUpdate: function (id) {
            axios({
                url: "manager/demand/toUpdate",
                params: {id: id}
            }).then(response => {
                layer.obj = response.data;
                layer.open({
                    type: 2,
                    content: "manager/demand/toUpdatePage",
                    area: ["80%", "80%"],
                    end: () => {
                        this.selectAll()
                    },
                });
            }).catch(error => console.log(error))
        },


        detail: function (id) {
            axios({
                url: "manager/demand/toUpdate",
                params: {id: id}
            }).then(response => {
                layer.obj = response.data;
                layer.open({
                    type: 2,
                    area: ["80%", "80%"],
                    content: "manager/demand/detail"
                })
            }).catch(error => console.log(error))
        }
    },
    created: function () {
        this.selectAll(1, 5);
    }

})