new Vue({
    el: ".main-content",
    data: function () {
        return {
            params: {},
            pageInfo: '',
            officeName: '全部',
            nodes: [],
            treeObj: '',
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

            addResourceNodes:[],
            addResourceTreeObj:{},

            addOfficeNodes:[],
            addOfficeTreeObj:{}

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
        },
        toUpdate: function (r) {
            layer.role = r;
            layer.open({
                type: 2,
                content: "manager/role/toUpdatePage",
                area: ["80%", "80%"],
            })
        },
        deleteById: function (id) {
            layer.confirm('确定要删除该角色？', {
                btn: ['删除', '取消'] //按钮
            }, () => {//确定删除
                axios({
                    url: "manager/role/deleteRole",
                    params: {rid: id}
                }).then(response => {
                    if (response.data.success) {
                        this.selectAll();
                    }
                    layer.msg(response.data.msg);
                }).catch(error => {
                    layer.msg(error.message)
                })
            }, function () {//取消
            });
        },
        detail: function (role) {
            layer.obj = role;
            layer.open({
                type: 2,
                content: "manager/role/toDetailPage",
                area: ["30%", "70%"]
            })
        },

        /*
            角色添加部分
         */
        initAddResourceTree:function () {
            axios({
                url: "manager/menu/list"
            }).then(response => {
                this.nodes = response.data;
                this.nodes[this.nodes.length] = {id: 0, name: "权限列表", open: true};
                this.selectByRid();
            }).catch(error => {
                layer.msg(error.message)
            })
        },

        initAddOfficeTree:function(){
            axios({
                url: "manager/office/list"
            }).then(response => {
                this.officeNodes = response.data;
                this.officeNodes[this.officeNodes.length] = {id: 0, name: "所有机构", open: true};
                this.selectOfficeByRid();
            })
        }


    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    },
    mounted: function () {
        this.initTree();
        this.initAddResourceTree();
        this.initAddOfficeTree();
        $("#userDataScope").chosen({width: "80%", search_contains: true});
        $("#saveUserDataScope").chosen({width: "50%", search_contains: true});
        $("#userDataScope").on("change", this.changeDataScope);
    }
});