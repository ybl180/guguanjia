new Vue({
    el: "#main-container",
    data: function () {
        return {
            pageInfo: '',
            params: {
                name: ''
            },
            nodes: [],
            treeObj: {},
            setting: {
                data: {
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
                    }
                },
                callback: {
                    onClick: this.onClick,
                },
                edit: {
                    enable: true,
                }
            }
        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: "manager/office/toList",
                method: "post",
                data: this.params,
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(error => layer.msg(error.message))
        },
        toUpdate: function (id) {
            axios({
                url: "manager/office/toUpdate",
                params: {oid: id}
            }).then(response => {
                layer.obj = response.data;
                layer.open({
                    type: 2,
                    content: "manager/office/toUpdatePage",
                    area: ["80%", "80%"],
                    end: () => {
                        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
                    }
                })
            }).catch()
        },
        initTree: function () {
            axios({
                url: "manager/office/list"
            }).then(response => {
                this.nodes = response.data;
                this.nodes[this.nodes.length] = {id: 0, name: '所有机构', open: true},
                    this.treeObj = $.fn.zTree.init($("#treeMenu"), this.setting, this.nodes);
            }).catch()
        },
        onClick: function (treeId, treeNode) {
            this.params.pid = treeNode.id;
            this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize)

        }
    },
    created: function () {
        this.selectAll(1, 5);
    },
    mounted: function () {
        this.initTree();
    }
})