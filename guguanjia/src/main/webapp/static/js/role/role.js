new Vue({
    el: ".main-content",
    data: function () {
        return {
            params: {},
            pageInfo: '',
            officeName: '全部',
            setting: {
                data: {
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
                    },
                }, callback: {
                    onClick: this.onClick,
                },
            },
            nodes: [],
            treeObj: ''
        }

    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: "manager/role/toList",
                method: "post",
                data: this.params,
            }).then(response => {
                this.pageInfo = response.data;
            }).catch()
        },
        initTree: function () {
            axios({
                url: "manager/office/list"
            }).then(response => {
                this.nodes = response.data;
                this.nodes[this.nodes.length] = {id: 0, name: '所有机构', open: true};
                this.treeObj = $.fn.zTree.init($("#pullDownTreeone"), this.setting, this.nodes)
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        onClick: function (event, treeId, treeNode) {
            if (treeNode.id != 0) {
                this.params.officeId = treeNode.id;
                this.officeName = treeNode.name;
            } else {
                this.params.officeId = '';
                this.officeName = "全部";
            }
            this.selectAll();
        },
        changeDataScope: function (e, param) {
            this.params.dataScope = param.selected;
        },

        managerUsers: function (id, name) {
            layer.roleId = id;
            layer.roleName = name;
            layer.open({
                type: 2,
                content: "manager/role/toRoleUserPage",
                area: ["80%", "80%"],
                title: "用户角色授权"
            })

        }
    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    },
    mounted: function () {
        this.initTree();
        this.initTree();
        $("#userDataScope").chosen({width: "80%", search_contains: true});
        $("#saveUserDataScope").chosen({width: "50%", search_contains: true});
        $("#userDataScope").on("change", this.changeDataScope);
    }
});