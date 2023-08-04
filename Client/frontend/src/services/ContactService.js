import axios from 'axios';

const CONTACT_API_ADD_CONTACT_ULR = 'http://localhost:8080/demo/add';
const CONTACT_API_N_AFTER_M_CONTACTS_ULR = 'http://localhost:8080/demo/nafterm';
const CONTACT_API_SEARCH_CONTACTS_URL = 'http://localhost:8080/demo/search';

class ContactService {

    // GET päring APIle, (m..m+n], by järgi sorteeritud kontakti kuvamiseks
    getNafterMcontacts(n, m, by) {
        var data = axios.get(CONTACT_API_N_AFTER_M_CONTACTS_ULR + "?n=" + n + "&m=" + m + "&by=" + by)
        return data;
    }

    // POST request nime, koodnime ja telefoninumbriga kontakti andmebaasi lisamiseks
    addContact(name, codename, phone) {
        axios.post(CONTACT_API_ADD_CONTACT_ULR, null, {
            params: {
                nimi: name,
                koodnimi: codename,
                telefon: phone
            }
        })
            .then(response => response.status)
            .catch(err => console.warn(err));
    }

    // Otsingusõna järgi kõik sobivad kontaktid GET päringuga
    searchContact(search) {
        return axios.get(CONTACT_API_SEARCH_CONTACTS_URL + "?search=" + search)
    }
}

export default new ContactService();