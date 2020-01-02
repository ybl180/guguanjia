new Vue({
    el: "#main-container",
    data: {
        pageInfo: '',
        app: {
            // platform: 0,
            // forceUpdate: 1//默认值
        }//新增APP使用对象
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            axios({
                url: "manager/app/toList",
                params: {pageNum: pageNum, pageSize: pageSize}
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(function (error) {
                console.log(error)
            })
        },
        toUpdate: function (id) {
            axios({
                url: "manager/app/toUpdate",
                params: {id: id}
            }).then(response => {
                layer.obj = response.data;
                layer.open({
                    type: 2,
                    content: 'manager/app/tpUpdatePage',
                    area: ["80%", "80%"],
                    end: () => {
                        this.selectAll();
                    }
                })
            }).catch(error => console.log(error));
        },

        insertApp: function () {
            axios({
                url: "manager/app/insertApp",
                method: "post",
                data: this.app
            }).then(response => {
                if (response.data.success) {//添加成功
                    this.selectAll();
                    //切换激活栏状态   siblings兄弟元素
                    $("#myTab").find("li[class='active']").attr('class', '').siblings().attr("class", "active");
                    $("#home").addClass("active");
                    $("#profile").removeClass("active");
                    this.app = {};
                }
                layer.msg(response.data.msg)
            }).catch(error => {
                console.log(error)
            })
        },

        deleteApp: function (id) {
            var _this=this;
            layer.confirm('是否删除？', {
                btn: ['确认', '取消']
            }, function () {
                axios({
                    url: "manager/app/deleteApp",
                    params: {id: id}
                }).then(response => {
                    _this.selectAll();
                    layer.msg(response.data.msg, {icon: 1});
                }).catch(error => {
                    console.log(error)
                });
            }, function () {
            });

        }
    },
    created: function () {
        this.selectAll(1, 3);
    }
});