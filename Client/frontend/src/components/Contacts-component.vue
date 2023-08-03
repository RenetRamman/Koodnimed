<template>
    <div class = "container">
            
            <h1 class = "text-center"> Kontaktid</h1>

            <table class = "table table-striped">
                <thead>
                    <tr>
                        <th> Nimi</th>
                        <th> Koodnimi</th>
                        <th> Telefon</th>
                    </tr>

                </thead>
                <tbody>
                    <tr v-for="contact in contacts" v-bind:key="contact.id">
                        <!-- <td> {{contact.id }}</td> -->
                        <td> {{contact.name }}</td>
                        <td> {{contact.codename}}</td>    
                        <td> {{contact.phone}}</td>
                    </tr>
                    <div>
                        <button v-if="previousAvailable" @click="getPreviousContacts(n, m-=n)">&lt;</button>
                        <button v-if="nextAvailable" @click="getNextContacts(n, m+=n)">&gt;</button>
                    </div>
                </tbody>
            </table>
            
            <!-- Input for new contacts -->
            <div>
                <p>Lisa kontakt</p>
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
            n: 10,
            m: 0,
            previousAvailable: false,
            nextAvailable: true
        }
    },
    methods: {
        getContacts(){
            ContactService.getContacts().then((response) => {
                this.contacts = response.data;   
            });
        },

        nextAndPreviousAvailable() {
            if (this.contacts.length == this.n) {
                    this.nextAvailable = true
            }
            else {
                this.nextAvailable = false
            }
            if (this.m != 0) {
                this.previousAvailable = true
            }
            else {
                this.previousAvailable = false
            }
        },

        getNextContacts(n, m) {
            ContactService.getNafterMcontacts(n, m).then((response) => {
                this.contacts = response.data;
                this.nextAndPreviousAvailable();
            });
        },

        getPreviousContacts(n, m) {
            ContactService.getNafterMcontacts(n, m).then((response) => {
                this.contacts = response.data;
                this.nextAndPreviousAvailable();
            });
        },

        addContact(name, codename, phone) {
            ContactService.addContact(name, codename, phone);
        }
    },
    created() {
        this.getNextContacts(this.n, 0);
    }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
th {
    padding-left: 40px;
}
td {
    padding-left: 40px;
}
table {
    text-align: center;
    margin-left: auto;
    margin-right: auto;
}

</style>