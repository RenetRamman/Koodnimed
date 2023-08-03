import axios from 'axios';

const CONTACT_API_ALL_CONTACTS_URL = 'http://localhost:8080/demo/all';
const CONTACT_API_ADD_CONTACT_ULR = 'http://localhost:8080/demo/add';
const CONTACT_API_N_AFTER_M_CONTACTS_ULR = 'http://localhost:8080/demo/nafterm';

class ContactService {

    getContacts() {
        var data = axios.get(CONTACT_API_ALL_CONTACTS_URL);
        console.log(data)
        return data;
    }

    getNafterMcontacts(n, m) {
        var data = axios.get(CONTACT_API_N_AFTER_M_CONTACTS_ULR + "?n=" + n + "&m=" + m)
        return data;
    }

    addContact(name, codename, phone) {
        axios.post(CONTACT_API_ADD_CONTACT_ULR, null, { params: {
            nimi:name,
            koodnimi:codename,
            telefon:phone
          }})
          .then(response => response.status)
          .catch(err => console.warn(err));
    }
}

export default new ContactService();