new Vue({
    el: "#main-container",
    data: function () {
        return {
            roleName: '',
            roleId: '',
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
                view: {
                    fontCss: this.fontCss,
                }
            },
            officeId: '',

            yxUsers: [],//已选用户组
            showRemoveClass: 'hide',//移除按钮样式，默认隐藏
            removeUserIds: [],      //选择移除的用户

            dxUsers: [],//待选用户组
            showAddClass: 'hide',//添加按钮，默认隐藏
            addUserIds: []       //选择添加的用户

        }
    },
    methods: {
        initTree: function () {
            axios({
                url: "manager/office/list"
            }).then(response => {
                this.nodes = response.data;
                this.nodes[this.nodes.length] = {id: 0, name: '所有机构', open: true};
                this.treeObj = $.fn.zTree.init($("#treeOffice"), this.setting, this.nodes)
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        onClick: function (event, treeId, treeNode) {
            let treeNodes = this.treeObj.transformToArray(this.treeObj.getNodes());
            for (let i = 0; i < treeNodes.length; i++) {
                if (treeNodes[i].id == treeNode.id) {
                    treeNodes[i].hightLight = true;
                } else {
                    treeNodes[i].hightLight = false;
                }
                this.treeObj.updateNode(treeNodes[i])
            }
            this.officeId = treeNode.id;
            // this.roleId= treeNode.id;
            // this.initYxUser();
            this.initDxUser()

        },
        fontCss: function (treeId, treeNode) {
            return treeNode.hightLight ? {color: "red"} : {color: "black"}
        },

        //根据当前roleId查询出已选用户
        initYxUser: function () {
            axios({
                url: "manager/sysuser/selectByRid",
                params: {rid: this.roleId}
            }).then(response => {
                this.yxUsers = response.data;
                for (let i = 0; i < this.yxUsers.length; i++) {
                    //将yxUsers的每个元素动态绑定一个用于checkbox的显示属性boolean值并且设置为false
                    this.yxUsers[i].show = false;
                }
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        //选择要移除的用户
        changYxUser: function (id) {
            for (let i = 0; i < this.yxUsers.length; i++) {
                if (this.yxUsers[i].id == id) {
                    //点击checkbox，修改其原show属性值为!show
                    this.yxUsers[i].show = !this.yxUsers[i].show;
                    if (this.yxUsers[i].show) {//打勾
                        this.showRemoveClass = 'show';//显示删除按钮
                        this.removeUserIds.push(this.yxUsers[i].id);//将选择的用户id放进保存数组
                        console.log(this.roleId);
                        console.log(this.removeUserIds);
                        return;
                    } else {//取消打勾
                        for (let j = 0; j < this.removeUserIds.length; j++) {
                            if (this.removeUserIds[j] == id) {
                                this.removeUserIds.splice(j);//将取消用户从保存的数组移除
                            }
                        }
                    }
                }
            }
            ;
            //最后一个打勾的取消，将移除按键隐藏
            if (this.removeUserIds.length == 0) {
                this.showRemoveClass = 'hide';
            }
        },
        removeUsers: function () {
            axios({
                url: "manager/role/deleteBatch",
                params: {
                    rid: this.roleId,
                    uids: this.removeUserIds + ''     //前端会封装成uids[]
                }
            }).then(response => {
                //移除操作完成   重新刷新yxUsers  隐藏移除人员按钮
                if (response.data.success) {
                    this.initYxUser();
                    this.showRemoveClass = 'hide';
                }
                layer.msg(response.data.msg)
            }).catch( error=> {
                layer.msg(error.message);
            })
        },

        initDxUser: function () {
            axios({
                url: "manager/sysuser/selectNoRole",
                params: {rid: this.roleId, oid: this.officeId}
            }).then(response => {
                this.dxUsers = response.data;
                for (let i = 0; i < this.dxUsers.length; i++) {
                    this.dxUsers[i].show = false;
                }
            }).catch(function (error) {
                layer.msg(error);
            })
        },
        changDxUser: function (id) {
            for (let i = 0; i < this.dxUsers.length; i++) {
                if (this.dxUsers[i].id == id) {
                    this.dxUsers[i].show = !this.dxUsers[i].show;
                    if (this.dxUsers[i].show) {//选中
                        this.showAddClass = 'show';
                        this.addUserIds.push(id);
                        return;
                    } else {//取消选中
                        for (let j = 0; j < this.addUserIds.length; j++) {
                            if (this.addUserIds[j] = id) {
                                this.addUserIds.splice(j)
                            }
                        }
                    }
                }
            }
            if (this.addUserIds.length == 0) {
                this.showAddClass = 'hide';
            }
        },
        insertUsers: function () {
            axios({
                url: 'manager/role/insertBatch',
                params: {rid: this.roleId, uids: this.addUserIds + ''}
            }).then(response => {
                //TODO
                layer.msg(response.data.msg);
                //更新当前的用户未授权列表
                this.dxUsers();
                this.showAddClass = 'hide';//隐藏提交按钮
                this.yxUsers();//更新已分配角色列表

            }).catch(function (error) {
                layer.msg(error);
            })
        }


    },
    created: function () {
        this.roleName = parent.layer.roleName;
        this.roleId = parent.layer.roleId;
    },
    mounted: function () {
        this.initTree();
        this.initYxUser();
    }
})