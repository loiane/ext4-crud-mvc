Ext.define('BrazilJS.controller.Contacts', {
    extend: 'Ext.app.Controller',

    stores: ['Contacts'],

    models: ['Contact'],

    views: ['contact.Edit', 'contact.List'],

    refs: [{
            ref: 'contactsPanel',
            selector: 'panel'
        },{
            ref: 'contactlist',
            selector: 'contactlist'
        }
    ],

    init: function() {
        this.control({
            'contactlist dataview': {
                itemdblclick: this.editUser
            },
            'contactlist button[action=add]': {
            	click: this.editUser
            },
            'contactlist button[action=delete]': {
                click: this.deleteUser
            },
            'contactedit button[action=save]': {
                click: this.updateUser
            }
        });
    },

    editUser: function(grid, record) {
        var edit = Ext.create('BrazilJS.view.contact.Edit').show();
        
        if(record){
        	edit.down('form').loadRecord(record);
        }
    },
    
    updateUser: function(button) {
        var win    = button.up('window'),
            form   = win.down('form'),
            record = form.getRecord(),
            values = form.getValues();
        
        
		if (values.id > 0){
			record.set(values);
		} else{
			record = Ext.create('BrazilJS.model.Contact');
			record.set(values);
			record.setId(0);
			this.getContactsStore().add(record);
		}
        
		win.close();
        this.getContactsStore().sync();
    },
    
    deleteUser: function(button) {
    	
    	var grid = this.getContactlist(),
    	record = grid.getSelectionModel().getSelection(), 
        store = this.getContactsStore();

	    store.remove(record);
	    this.getContactsStore().sync();
    }
});
