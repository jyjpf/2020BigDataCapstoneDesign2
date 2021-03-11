<template>
  <div>
    <v-card class="text-center pa-1">
      <v-card-title class="justify-center display-1 mb-2">패스워드 찾기</v-card-title>
      <v-card-subtitle>
        이메일을 입력하시고 인증번호를 입력하시면 비밀번호가 초기화됩니다.
      </v-card-subtitle>

      <!-- reset form -->
      <v-card-text>
        <v-form ref="form" v-model="isFormValid" lazy-validation @submit.prevent="submit">
          <v-text-field
            v-model="email"
            :rules="[rules.required]"
            :validate-on-blur="false"
            :error="error"
            :error-messages="errorMessages"
            label="Email" 
            name="email"
            outlined
            @keyup.enter="submit"
            @change="resetErrors"
          />

          <v-btn
            :loading="isLoading"
            block
            x-large
            color="primary"
            @click="submit"
          >메일 보내기</v-btn>
        </v-form>
      </v-card-text>
    </v-card>

    <div class="text-center mt-6">
      <router-link to="/auth/signin">
        로그인 화면으로
      </router-link>
    </div>
  </div>
</template>

<script>
/*
|---------------------------------------------------------------------
| Forgot Page Component
|---------------------------------------------------------------------
|
| Template to send email to remember/replace password
|
*/
export default {
  data() {
    return {
      // reset button
      isLoading: false,
      // form
      isFormValid: true,
      email: '',
      // form error
      error: false,
      errorMessages: '',
      // input rules
      rules: {
        required: (value) => (value && Boolean(value)) || 'Required'
      }
    }
  },
  methods: {
    submit(e) {
 
    },
    resetEmail(email, password) {
    },
    resetErrors() {
      this.error = false
      this.errorMessages = ''
    }
  }
}
</script>