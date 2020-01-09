new Vue({
    el: "#main-container",
    data: function () {
        return {
            role: {},
            setting: {
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
            nodes: [],
            treeObj: {},
            resources: [],//存放当前角色的权限数组

            officeNodes: [],
            officeTreeObj: {},
            offices: [],

            params: {
                resIds: '',
                role: this.role
            }
        }

    },
    methods: {
        doUpdata: function () {
            axios({
                url: "manager/role/doUpdate",
                method: 'post',
                data: this.params,
            }).then(response=>{

            })


        },
        initTree: function () {
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
        selectByRid: function () {
            console.log("selectByRid")
            axios({
                url: "manager/menu/selectByRid",
                params: {rid: this.role.id}
            }).then(response => {
                this.resources = response.data;
                for (let i = 0; i < this.nodes.length; i++) {//遍历所有的资源节点找到需要设置选中的节点，设置选中
                    for (let j = 0; j < this.resources.length; j++) {
                        if (this.nodes[i].id == this.resources[j].id) {
                            // this.treeObj.updateNode(this.nodes[i], true);
                            this.nodes[i].checked = true;
                            break;
                        }
                    }
                }
                this.treeObj = $.fn.zTree.init($("#select-treetreeSelectResEdit"), this.setting, this.nodes);
                this.params.resIds = this.treeObj.getCheckedNodes(true)
            }).catch(error => {
                layer.msg(error.message)
            })
        },

        initOfficeTree: function () {
            axios({
                url: "manager/office/list"
            }).then(response => {
                this.officeNodes = response.data;
                this.officeNodes[this.officeNodes.length] = {id: 0, name: "所有机构", open: true};
                this.selectOfficeByRid();
            })
        },
        selectOfficeByRid: function () {
            axios({
                url: "manager/office/selectByRid",
                params: {rid: this.role.id}
            }).then(response => {
                this.offices = response.data;
                for (let i = 0; i < this.officeNodes.length; i++) {
                    for (let j = 0; j < this.offices.length; j++) {
                        if (this.officeNodes[i].id == this.offices[j].id) {
                            this.officeNodes[i].checked = true;
                            break;
                        }
                    }
                }
                this.officeTreeObj = $.fn.zTree.init($("#select-treetreeSelectOfficeEdit"), this.setting, this.officeNodes);
            })
        },
        changeDataScope: function (e, param) {
            if (param.selected != 9) {
                $("#select-treetreeSelectOfficeEdit").css("display", "none")
            } else {
                $("#select-treetreeSelectOfficeEdit").css("display", "")
                this.initOfficeTree();
            }
            console.log(this.params.resIds)

        },
        onCheck: function (event, treeId, treeNode) {
            this.params.resIds = this.treeObj.getCheckedNodes(true)
        }


    },
    created: function () {
        this.role = parent.layer.role;
        this.initTree();

    },
    mounted: function () {

        $("#chosenSelectEdit").chosen({width: "40%", search_contains: true});
        if (this.role.dataScope == 9) {
            this.initOfficeTree();
        }
        $("#chosenSelectEdit").on("change", this.changeDataScope)
    }
});