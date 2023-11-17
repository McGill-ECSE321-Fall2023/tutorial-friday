<template>
  <div id="users-component">
    <h1>Event Registration</h1>
    <h2>Users</h2>
    <table>
      <tbody>
        <tr>
          <th>Name</th>
          <th>Verified</th>
        </tr>
        <tr v-for="p in people">
          <td>{{ p.name }}</td>
          <td>{{ p.isVerified ? "Yes" : "No" }}</td>
        </tr>
      </tbody>
    </table>
    <h2>New User</h2>
    <div>
      <input placeholder="Name" v-model="newPersonName" />
      <input placeholder="Password" v-model="newPersonPassword" />
      <!--
        We can use createPersonErrors directly, even if it's a string.
        JavaScript automatically interprets non-empty strings as true and empty strings as false.
      -->
      <button id="create-person-btn" @click="createUser()" v-bind:disabled="createPersonErrors">Create</button>
    </div>
    <p class="error-msg">{{ createPersonErrors }}</p>
  </div>
</template>

<script>
export default {
  name: "Users",
  data() {
    return {
      people: [
        { name: "Alice", isVerified: true },
        { name: "Bob", isVerified: false },
      ],
      newPersonName: "",
      newPersonPassword: "",
    };
  },
  methods: {
    createUser() {
      this.people.push({ name: this.newPersonName, isVerified: false });
      // Vue properties are reactive: updating the DOM updates the property and updating the property also updates the DOM
      this.newPersonName = "";
      this.newPersonPassword = "";
    }
  },
  computed: {
    createPersonErrors() {
      let errorMessage = "";
      if (!this.newPersonName || !this.newPersonName.trim()) {
        errorMessage += "Missing name. ";
      }
      if (!this.newPersonPassword || !this.newPersonPassword.trim()) {
        errorMessage += "Missing password. ";
      }
      // Use ===, not ==
      // https://dorey.github.io/JavaScript-Equality-Table/
      return errorMessage;
    }
  }
};
</script>

<style>
/* Select by ID */
/* Comment out this particular style because it would make the button look the same whether or not it's disabled. */
/* #create-person-btn {
  color: darkgreen;
  background-color: lightgreen;
  border: 1px dashed darkgreen;
} */

#users-component {
  display: flex;
  flex-direction: column;
  align-items: baseline;
  padding: 2%;
}

/* Select by tag name h1 OR h2 */
h1,
h2 {
  text-decoration: 1px solid underline;
}

table {
  border: 1px solid black;
  align-self: stretch;
}

th,
td {
  border: 1px solid black;
  padding: 5px;
}

body {
  display: flex;
  flex-direction: column;
  align-items: stretch;
}

/* Select by class */
.error-msg {
  color: red;
}
</style>