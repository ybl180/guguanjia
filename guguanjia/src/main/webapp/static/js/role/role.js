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

            /*
               添加角色模块
             */
            addResourceNodes: [],
            addResourceTreeObj: {},

            addOfficeNodes: [],
            addOfficeTreeObj: {},

            addTreeSetting: {
                data: {
                    simpleData: {
                        enable: true,
                        pIdKey: "parentId"
                    }
                },
                check: {//设置checkbox 处理
                    enable: true,
                    chkboxType: {"Y": "ps", "N": "ps"},
                    chkStyle: "checkbox",
                    autoCheckTrigger: true
                },
                callback: {
                    onCheck: this.onCheck
                }
            },
            addRole: {
                resIds: [],
                officeIds: [],
                role: {
                    dataScope: 9,    //按明细设置
                    officeId: '',
                },
                officeName: ''
            }

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
                area: ["70%", "70%"]
            })
        },

        /*
            角色添加部分
         */
        initAddResourceTree: function () {
            axios({
                url: "manager/menu/list"
            }).then(response => {
                this.addResourceNodes = response.data;
                this.addResourceNodes[this.addResourceNodes.length] = {id: 0, name: "权限列表", open: true};
                this.addResourceTreeObj = $.fn.zTree.init($("#select-treetreeSelectRes"), this.addTreeSetting, this.addResourceNodes);
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        initAddOfficeTree: function () {
            axios({
                url: "manager/office/list"
            }).then(response => {
                this.addOfficeNodes = response.data;
                this.addOfficeNodes[this.addOfficeNodes.length] = {id: 0, name: "所有机构"};
                this.addOfficeTreeObj = $.fn.zTree.init($("#select-treetreeSelectOffice"), this.addTreeSetting, this.addOfficeNodes);
            })
        },
        onCheck: function (event, treeId, treeNode) {
            this.addRole.resIds = [];
            //获取树中复选框选中的资源
            let checkedNodes = this.addResourceTreeObj.getCheckedNodes(true);
            for (let i = 0; i < checkedNodes.length; i++) {
                this.addRole.resIds.push(checkedNodes[i].id);
            }
            //跨公司officeIds
            this.addRole.officeIds = [];
            let checkedOfficeNodes = this.addOfficeTreeObj.getCheckedNodes(true);
            for (let i = 0; i < checkedOfficeNodes.length; i++) {
                this.addRole.officeIds.push(checkedOfficeNodes[i].id);
            }

            console.log(this.params)
        },

        changeAddRoleDataScope: function (e, param) {
            this.addRole.role.dataScope = param.selected;
            if (param.selected != 9) {
                $("#select-treetreeSelectOffice").css("display", "none")
            } else {
                $("#select-treetreeSelectOffice").css("display", "");
                this.initAddOfficeTree();
            }
        },
        selectOffice: function () {
            layer.roleOfficeName = this.addRole.officeName;
            layer.roleOfficeId = this.addRole.role.officeId;
            layer.open({
                type: 2,
                content: "manager/role/roleSelectOffice",
                area: ["80%", "80%"],
                end: () => {
                    this.addRole.officeName = layer.changeOfficeName;
                    this.addRole.role.officeId = layer.changeOfficeId
                }
            })
        },
        //保存用户
        saveRole: function () {
            axios({
                url: "manager/role/saveRole",
                method: "post",
                data: this.addRole
            }).then(response => {
                if (response.data.success) {//添加成功
                    this.selectAll();
                    //切换激活栏状态   siblings兄弟元素
                    $("#myTab").find("li[class='active']").attr('class', '').siblings().attr("class", "active");
                    $("#home").addClass("active");
                    $("#profile").removeClass("active");
                    //清空数据
                    this.addRole = {
                        resIds: [],
                        officeIds: [],
                        role: {
                            dataScope: 9,    //按明细设置
                            officeId: '',
                        },
                        officeName: ''
                    };
                    this.initAddOfficeTree();
                    this.initAddResourceTree();
                    $("#select-treetreeSelectOffice").css("display", "");
                }
                layer.msg(response.data.msg)
            }).catch(error => {
                console.log(error.message)
            })
        }


    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    },
    mounted: function () {
        this.initTree();
        $("#userDataScope").chosen({width: "80%", search_contains: true});
        $("#userDataScope").on("change", this.changeDataScope);

        //添加
        this.initAddResourceTree();
        $("#saveUserDataScope").chosen({width: "50%", search_contains: true});
        if (this.addRole.role.dataScope == 9) {
            this.initAddOfficeTree();
        }
        $("#saveUserDataScope").on("change", this.changeAddRoleDataScope)
    },
    updated:function () {
        $("#saveUserDataScope").trigger("chosen:updated");
    }
});