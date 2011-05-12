Ext.define('BrazilJS.view.contact.List' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.contactlist',
    
    iconCls: 'icon-grid',

    title : 'Contacts',
    store: 'Contacts',

    columns: [{
    	header: "NAME",
		width: 170,
		flex:1,
		dataIndex: 'name'
	},{
		header: "PHONE #",
		width: 160,
		flex:1,
		dataIndex: 'phone'
	},{
		header: "EMAIL",
		width: 170,
		flex:1,
		dataIndex: 'email'
	}],
	
	initComponent: function() {
		
		this.dockedItems = [{
            xtype: 'toolbar',
            items: [{
                iconCls: 'icon-save',
                itemId: 'add',
                text: 'Add',
                action: 'add'
            },{
                iconCls: 'icon-delete',
                text: 'Delete',
                action: 'delete'
            }]
        },
        {
            xtype: 'pagingtoolbar',
            dock:'top',
            store: 'Contacts',
            displayInfo: true,
            displayMsg: 'Displaying contacts {0} - {1} of {2}',
            emptyMsg: "No contacts to display"
        }];
		
		this.callParent(arguments);
	}
});
