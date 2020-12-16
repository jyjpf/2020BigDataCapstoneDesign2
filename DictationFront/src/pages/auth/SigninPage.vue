<template>
  <div>
    <v-alert :value="errorlogin" type="error">
      이메일와 비밀번호를 확인해주세요.
    </v-alert>
    <v-alert :value="errorserver" type="error">
      서버와의 통신에 실패하였습니다.<br>
      관리자에게 문의하여 주세요.
    </v-alert>
    <v-card class="text-center pa-1">
      <v-card-title class="justify-center display-1 mb-2">로그인</v-card-title>

      <!-- sign in form -->
      <v-card-text>
        <v-form ref="form" v-model="isFormValid" lazy-validation>
          <v-text-field
            v-model="id"
            :rules="[rules.required]"
            :validate-on-blur="false"
            :error="error"
            label="아이디"
            name="id"
            outlined
            @keyup.enter="submit"
            @change="resetErrors"
          ></v-text-field>

          <v-text-field
            v-model="password"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[rules.required]"
            :type="showPassword ? 'text' : 'password'"
            :error="error"
            :error-messages="errorMessages"
            label="비밀번호"
            name="password"
            outlined
            @change="resetErrors"
            @keyup.enter="submit"
            @click:append="showPassword = !showPassword"
          ></v-text-field>

          <v-btn
            :loading="isLoading"
            :disabled="isSignInDisabled"
            block
            x-large
            color="primary"
            @click="submit"
          >로그인</v-btn>

          <div class="mt-5">
            <router-link style="margin: 5px" to="/auth/forgot-password">
              아이디 찾기
            </router-link>
            <router-link style="margin: 5px" to="/auth/forgot-password">
              비밀번호 찾기
            </router-link>
          </div>
        </v-form>
      </v-card-text>
    </v-card>

    <div class="text-center mt-6">
      아이디가 없으신가요?
      <router-link to="/auth/signup" class="font-weight-bold">
        회원가입하기
      </router-link>
    </div>
  </div>
</template>

<script>
/*
|---------------------------------------------------------------------
| Sign In Page Component
|---------------------------------------------------------------------
|
| Sign in template for user authentication into the application
|
*/
import axios from 'axios'
import { mapActions } from 'vuex'

export default {
  data() {
    return {
      // sign in buttons
      isLoading: false,
      isSignInDisabled: false,

      // form
      isFormValid: true,
      id: '',
      password: '',

      // form error
      error: false,
      errorMessages: '',

      errorlogin: false,
      errorserver: false,

      // show password field
      showPassword: false,

      // input rules
      rules: {
        required: (value) => (value && Boolean(value)) || '필수 항목입니다'
      }
    } 
  },
  methods: {
    ...mapActions('dictation-app', ['setRole']),
    submit() {
      if (this.$refs.form.validate()) {
        this.isLoading = true
        this.isSignInDisabled = true
        this.signIn(this.id, this.password)
      }
    },
    signIn(pId, pPassword) {
      axios.post('/api/common/login', { user_id: pId, pw: pPassword })
        .then((res) => {
          sessionStorage.setItem('X-AUTH-TOKEN', res.data.token) 
          axios.defaults.headers.common['X-AUTH-TOKEN'] = res.data.token
          this.setRole(res.data.role)
          this.$router.push('/dashboard/analytics')
        })
        .catch((err) => {
          console.log(err)
          this.errorlogin = true
        })
        .then(() => {
          this.isLoading = false
          this.isSignInDisabled = false
        })
    },
    signInProvider(provider) {},
    resetErrors() {
      this.error = false
      this.errorMessages = ''

      this.errorProvider = false
      this.errorProviderMessages = ''
    }
  }
}
</script>
