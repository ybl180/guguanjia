new Vue({
    el: "#main-container",
    data: function () {
        return {
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
            },
            nodes: [],
            treeObj: {},
            name: '',
            id: ''
        }

    },
    methods: {
        initTree: function () {
            axios({
                url: "manager/area/selectAll"
            }).then(response => {
                this.nodes = response.data;
                this.treeObj = $.fn.zTree.init($("#select-tree"), this.setting, this.nodes)
            }).catch(error => layer.msg(error.message))
        },
        onClick: function (event, treeId, treeNode) {
            this.id = treeNode.id;
            this.name = treeNode.name;
        },
        choose: function () {
            parent.layer.parentId = this.id;
            parent.layer.parentName = this.name;
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }


    },
    mounted: function () {
        this.initTree();
    },
});