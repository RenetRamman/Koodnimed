<template>
    <div class = "container">
            
            <h1 class = "text-center"> Contact List</h1>

            <table class = "table table-striped">
                <thead>
                    <tr>
                        <th> Name</th>
                        <th> Codename</th>
                        <th> Phone</th>
                    </tr>

                </thead>
                <tbody>
                    <tr v-for="contact in contacts" v-bind:key="contact.id">
                        <td> {{contact.id }}</td>
                        <td> {{contact.name }}</td>
                        <td> {{contact.codename}}</td>    
                        <td> {{contact.phone}}</td>
                    </tr>
                </tbody>
            </table>
            
            <!-- Input for new contacts -->
            <div>
                <input v-model="name" placeholder="Nimi">
                <input v-model="codename" placeholder="Koodnimi">
                <input v-model="phone" placeholder="telefon">
                
                <button @click="addContact(name, codename, phone)">lisa</button>
            </div>
        </div>
</template>

<script>

import ContactService from '../services/ContactService';


export default {
    name: 'Contacts-data',
    data(){
        return {
            contacts: [],
        }
    },
    methods: {
        getContacts(){
            ContactService.getContacts().then((response) => {
                this.contacts = response.data;   
            });
        },

        addContact(name, codename, phone) {
            ContactService.addContact(name, codename, phone);
        }
    },
    created() {
        this.getContacts();
    }
}

</script>