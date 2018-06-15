var zTree;
    var demoIframe;

    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
        var addStr = "<span class='button remove' id='removeBtn_" + treeNode.tId
                + "' title='add node' onfocus='this.blur();'></span>";

        addStr += "<span class='button add' id='addBtn_" + treeNode.tId + "'></span>";
        addStr += "<span class='button edit' id='editBtn_" + treeNode.tId + "'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_"+treeNode.tId);
        if (btn) btn.bind("click", function(){
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.addNodes(treeNode, {id:(1000 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
            return false;
        });
    };

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
        $("#removeBtn_"+treeNode.tId).unbind().remove();
        $("#editBtn_"+treeNode.tId).unbind().remove();
    };

    var setting = {
        check: {
            enable: true
        },
        view: {
            //addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            beforeClick: function(treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("tree");
                if (treeNode.isParent) {
                    zTree.expandNode(treeNode);
                    return false;
                } else {
                    demoIframe.attr("src",treeNode.file + ".html");
                    return true;
                }
            }
        }
    };

    var zNodes =[
        {id:1, pId:0, name:"运营部", open:true},
        {id:101, pId:1, name:"第一团队", file:"core/standardData"},
        {id:101001, pId:101, name:"刘某【10001】", file:"core/standardData"},
        {id:101002, pId:101, name:"邓某【10002】", file:"core/standardData"},
        {id:101003, pId:101, name:"李某【10003】", file:"core/standardData"},
        
        
        {id:102, pId:1, name:"第二团队", file:"core/simpleData"},
        {id:102001, pId:102, name:"刘某【10001】", file:"core/standardData"},        
        {id:102002, pId:102, name:"刘某【10002】", file:"core/standardData"},
        {id:102003, pId:102, name:"李某【10003】", file:"core/standardData"},
        
        {id:103, pId:1, name:"第三团队", file:"core/noline"},
        {id:103001, pId:103, name:"刘某【10001】", file:"core/standardData"},        
        {id:103002, pId:103, name:"刘某【10002】", file:"core/standardData"},
        {id:103003, pId:103, name:"李某【10003】", file:"core/standardData"},

        {id:2, pId:0, name:"质量部", open:false},
        {id:201, pId:2, name:"刘某【10001】", file:"excheck/checkbox"},
        {id:206, pId:2, name:"邓某【10002】", file:"excheck/checkbox_nocheck"}

    ];

    $(document).ready(function(){
        var t = $("#tree");
        t = $.fn.zTree.init(t, setting, zNodes);
        demoIframe = $("#testIframe");
        demoIframe.bind("load", loadReady);
        var zTree = $.fn.zTree.getZTreeObj("tree");
        zTree.selectNode(zTree.getNodeByParam("id", 101));

    });

    function loadReady() {
        var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
                htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
                maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
                h = demoIframe.height() >= maxH ? minH:maxH ;
        if (h < 530) h = 530;
        demoIframe.height(h);
    }
