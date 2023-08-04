<template>
  <div class="container">
    <h1 class="text-center">Kontaktid</h1>

    <!-- Kontaktide list -->
    <table class="table table-striped">
      <thead>
        <tr>
          <th>
            <button
              @click="
                setSortCriteria('name');
                getNextContacts(n, 0);
              "
            >
              Nimi
            </button>
          </th>
          <th>
            <button
              @click="
                setSortCriteria('codename');
                getNextContacts(n, 0);
              "
            >
              Koodnimi
            </button>
          </th>
          <th>
            <button
              @click="
                setSortCriteria('phone');
                getNextContacts(n, 0);
              "
            >
              Telefon
            </button>
          </th>
        </tr>
        <p>{{ sortBy }} {{ sortDirection }}</p>
      </thead>
      <tbody>
        <tr v-for="contact in contacts" v-bind:key="contact.id">
          <!-- <td> {{contact.id }}</td> -->
          <td>{{ contact.name }}</td>
          <td>{{ contact.codename }}</td>
          <td>{{ contact.phone }}</td>
        </tr>
        <div>
          <button
            v-if="previousAvailable"
            @click="getPreviousContacts(n, (m -= n))"
          >
            &lt;
          </button>
          <button v-if="nextAvailable" @click="getNextContacts(n, (m += n))">
            &gt;
          </button>
        </div>
      </tbody>
    </table>

    <!-- Kontaktide otsimine -->
    <div>
      <h4>Otsi kontakti</h4>
      <input v-model="search" placeholder="Otsi" />

      <button @click="searchContact(search)">Otsi</button>
    </div>

    <!-- Uue kontakti lisamine -->
    <div>
      <p>Lisa kontakt</p>
      <input v-model="name" placeholder="Nimi" />
      <input v-model="codename" placeholder="Koodnimi" />
      <input v-model="phone" placeholder="Telefon" />

      <button @click="addContact(name, codename, phone)">Lisa</button>
    </div>
  </div>
</template>

<script>
import ContactService from "../services/ContactService";

export default {
  name: "Contacts-data",
  data() {
    return {
      contacts: [],
      n: 10,
      m: 0,
      previousAvailable: false,
      nextAvailable: true,
      sortBy: "name",
      sortDirection: "asc",
    };
  },
  methods: {
    // Kontaktide sorteerimise kriteeriumi määramine
    setSortCriteria(by) {
      this.contacts = [];
      if (this.sortBy === by) {
        if (this.sortDirection === "asc") {
          this.sortDirection = "desc";
        } else {
          this.sortDirection = "asc";
        }
      } else {
        this.sortBy = by;
        this.sortDirection = "asc";
      }
    },

    // Kas järgmiste / eelmiste kontaktide laadimise nupp peab olema peidetud
    nextAndPreviousAvailable() {
      if (this.contacts.length == this.n) {
        this.nextAvailable = true;
      } else {
        this.nextAvailable = false;
      }
      if (this.m != 0) {
        this.previousAvailable = true;
      } else {
        this.previousAvailable = false;
      }
    },

    // Järgmiste kontaktide pärimine APIst
    getNextContacts(n, m) {
      ContactService.getNafterMcontacts(
        n,
        m,
        this.sortBy + "_" + this.sortDirection
      ).then((response) => {
        this.contacts = response.data;
        var last = this.contacts.pop();
        this.contacts.push(last);
        this.nextAndPreviousAvailable();
      });
    },

    // Eelmiste kontaktide pärimine APIst
    getPreviousContacts(n, m) {
      ContactService.getNafterMcontacts(
        n,
        m,
        this.sortBy + "_" + this.sortDirection
      ).then((response) => {
        this.contacts = response.data;
        this.nextAndPreviousAvailable();
      });
    },

    // Kontakti andmebaasi lisamine, võttes nime, koodnime ja telefoni. argumendi puudumisel lisab API sellele vaikimisi-väärtuse
    addContact(name, codename, phone) {
      ContactService.addContact(name, codename, phone);
    },

    // Kontaktide otsimine API kaudu märksõna järgi
    searchContact(search) {
      ContactService.searchContact(search).then((response) => {
        this.contacts = response.data;
        this.nextAndPreviousAvailable();
      });
    },
  },
  created() {
    this.getNextContacts(this.n, 0, this.sortBy + "_" + this.sortDirection);
  },
};
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