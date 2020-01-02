new Vue({
    el: "#main-container",
    data: {
        params: {
            type: ''
        },
        pageInfo: '',
        statute: {type: ''},
        // msg: '<h2><img src="http://img.baidu.com/hi/jx2/j_0003.gif"/>Vue + UEditor + v-model双向绑定</h2>',
        ueditorConfig: {
            UEDITOR_HOME_URL: 'static/ueditor/',
            serverUrl: 'doExec'          // "ueditor/doExec"
        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: "manager/statute/toList",
                method: "post",
                data: this.params,
            }).then(response => {
                this.pageInfo = response.data;
                console.log(this.pageInfo)
            }).catch(error => console.log(error))
        },

        insertStatute: function () {
            axios({
                url: "manager/statute/insert",
                method: "post",
                data: this.statute
            }).then(response => {
                if (response.data.success) {//添加成功
                    this.selectAll();
                    //切换激活栏状态   siblings兄弟元素
                    $("#myTab").find("li[class='active']").attr('class', '').siblings().attr("class", "active");
                    $("#home").addClass("active");
                    $("#profile").removeClass("active");
                    this.statute = {type: ''};
                }
                layer.msg(response.data.msg)
            }).catch(error => {
                console.log(error)
            })
        },

        toUpdate: function (id, uploadUserOfficeId) {
            axios({
                url: "manager/statute/toUpdate",
                params: {id: id}
            }).then(response => {
                    layer.obj = response.data;
                    layer.offerId = uploadUserOfficeId;
                    layer.open({
                        type: 2,
                        area: ["80%", "80%"],
                        content: "manager/statute/toUpdatePage",
                        end: () => {
                            this.selectAll()
                        }
                    })
                }
            ).catch()
        }


    },
    created: function () {
        this.selectAll(1, 5);
    },
    components: {//引入vue组件
        VueUeditorWrap
    }
});