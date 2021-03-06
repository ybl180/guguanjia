new Vue({
    el: "#main-container",
    data: function () {
        return {
            params: {
                userNo: '',
                name: '',
                officeId: '',
                roleIds: '',
            },
            nodes: [],
            treeObj: '',
            officeName: '全部',
            searchName: '',
            pageInfo: '',
            setting: {
                data: {
                    simpleData: {
                        enable: true,
                        pIdKey: "parentId",
                    }
                },
                callback: {
                    onClick: this.onClick,
                },
                view: {
                    fontCss: this.fontCss,
                }
            },
            Roles: [],
            paramsRoles: []

        }

    },
    methods: {
        selectAll: function (pageNum, pageSize) {

            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: "manager/sysuser/toList",
                method: "post",
                data: this.params,
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        all: function () {
            this.params.userNo = '';
            this.params.name = '';
            this.params.officeId = '';
            this.officeName = '全部';
            this.selectAll()
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
            this.params.officeId = treeNode.id;
            this.officeName = treeNode.name;
            this.selectAll(1, 5);
        },
        search: function () {
            //获取树对象
            let zTreeObj = $.fn.zTree.getZTreeObj("pullDownTreeone");
            //获取所有树数据，将他转换成简单数组
            let zTreeNodes = zTreeObj.transformToArray(zTreeObj.getNodes());
            //进行模糊查询匹配到所有匹配到的字节数组
            let nodes = zTreeObj.getNodesByParamFuzzy("name", this.searchName, null);
            //遍历先清除高亮
            for (let i = 0; i < zTreeNodes.length; i++) {
                zTreeNodes[i].hightLight = false;
                //更新树对象
                this.treeObj.updateNode(zTreeNodes[i]);
            }
            for (let i = 0; i < nodes.length; i++) {
                nodes[i].hightLight = true;
                this.treeObj.updateNode(nodes[i]);
                //展开指定的节点
                this.treeObj.expandNode(nodes[i].getParentNode(), true, false, false);
            }
        },
        fontCss: function (treeId, treeNode) {
            return treeNode.hightLight ? {color: "red"} : {color: "black"}
        },
        createRole: function () {
            axios({
                url: "manager/sysuser/selectRole"
            }).then(response => {
                this.Roles = response.data;
                for (let i = 0; i < this.Roles.length; i++) {
                    this.Roles[i].show = false;//查询条件中不存在
                }
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        addRolesCondition: function (e, param) {
            for (let i = 0; i < this.Roles.length; i++) {
                if (param.selected != undefined && this.Roles[i].id == param.selected) {
                    this.paramsRoles.push(param.selected);
                    break;
                } else if (this.Roles[i].id == param.deselected) {
                    for (let j = 0; j < this.paramsRoles.length; j++) {
                        if (this.paramsRoles[j] == param.deselected) {
                            this.paramsRoles.splice(j);
                            break;
                        }
                    }
                }
            }
            this.params.roleIds = this.paramsRoles;
            console.log(this.paramsRoles)
        },
        detailUser: function (user) {
            layer.user = user;
            layer.open({
                type: 2,
                content: "manager/sysuser/detail",
                area: ["80%", "80%"],
                title: "用户详情"
            })
        },
        toUpdate: function (user) {
            layer.user = user;
            layer.open({
                type: 2,
                content: "manager/sysuser/toUpdate",
                area: ["80%", "80%"],
                title: "用户详情"
            })
        }

    },
    created: function () {
        this.selectAll(1, 5);
        this.createRole();
    },
    mounted: function () {
        this.initTree();
        $("#role-select").chosen({width: "80%", search_contains: true});
        $("#role-select").on("change", this.addRolesCondition);

        $("#role-choose").chosen({width: "100%", search_contains: true});
    },
    updated: function () {
        $("#role-select").trigger("chosen:updated")

    }
})