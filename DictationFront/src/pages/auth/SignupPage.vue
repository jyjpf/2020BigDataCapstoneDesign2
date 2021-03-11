<template>
  <div>
    <v-stepper v-model="e1">
      <v-stepper-header>
        <v-stepper-step
          :complete="e1 > 1"
          step="1"
        >개인정보 동의 및 학교선택</v-stepper-step>
        <v-divider/>
        <v-stepper-step
          :complete="e1 > 2"
          step="2"
        >개인정보 입력</v-stepper-step>
      </v-stepper-header>
      <v-stepper-items>
        <v-stepper-content step="1">
          <v-form
            ref="step1"
            lazy-validation
          >
            <v-autocomplete
              v-model="user.school_cd"
              :items="schoolList"
              :rules="[rules.required]"
              filled
              label="학교 선택"
              no-data-text="입력하신 학교가 등록되지 않았거나 잘못되었습니다"
            />
            <v-card
              max-height="450px"
              elevation="0"
              style="overflow: auto"
            >
              <v-card-text style="text-align: left">
                <p>
                  회원 가입을 신청하시는 분께 개인정보의 수집과 이용 목적, 개인정보 보관과 이용 기간에 대해 알려드립니다.<br>
                  회원 가입을 원하시는 경우, 아래 내용을 자세히 읽고 동의해주세요.<br>
                </p>

                <h3>1. 수집하는 개인정보</h3>

                <p>
                  이용자를 확인하고, 아이디를 만들기 위해 필요한 정보를 수집합니다.<br>
                  [필수항목] 아이디, 비밀번호, 학교, 이름, 학년, 반, 번호, 성별, 생년월일<br>
                  또한 서비스를 이용할 때 자동으로 만들어지는 정보(IP주소, 쿠키, 서비스 이용 기록)들이 저장, 수집될 수 있습니다.<br>
                </p>

                <h3>2. 수집한 개인정보의 이용</h3>
                <p>
                  수집한 개인정보는 관련 서비스(모바일 웹/앱 포함)를 위해 아래의 목적으로만 이용합니다.<br>
                </p>
                <p>
                  회원 관리를 위해<br>
                  이용자가 직접 회원 가입 또는 탈퇴를 신청한 것이 맞는지 확인, 이용자의 학교, 반, 번호 확인, 이용자 구분 등이 필요한 경우 개인정보를 이용합니다.<br>
                </p>
                <p>
                  새로운 서비스 개발과 향상을 위해<br>
                  원래의 서비스를 제공하기 위해서도 이용하지만, 더 편리하고 좋게 만드는데 개인정보를 이용합니다. 
                  학생의 능력치를 분석하여 더 나은 솔루션과 분석들을 제공합니다.
                </p>
                <p>
                  이용자 보호와 서비스 운영을 위해<br>
                  옳지 못한 방법으로 서비스를 이용하고 운영에 피해를 주는 행동을 못하게 하는데 이용합니다. 
                  그리고 다른 사람의 정보(아이디, 비밀번호 등)를 몰래 이용하는 행동 등을 못하게 하기 위해 개인정보를 이용합니다.
                </p>

                <h3>3. 개인정보의 파기(삭제)</h3>
                <p>
                  이용자의 개인정보는 회원 탈퇴 시 빠르게 삭제합니다.
                  그러나 아래와 같이 이용자에게 따로 동의를 받는 경우에는 동의 받은 기간 동안 안전하게 보관하였다가 삭제합니다.
                  그리고 법에서 보관 기간을 정해두고 있는 경우에도 해당 기간 동안 개인정보를 안전하게 보관하였다가 삭제합니다.
                </p>
                <p>
                  다른 이용자 보호와 안전한 서비스 이용을 위한 경우
                  옳지 못한 방법으로 서비스를 이용하는 것을 막기 위해 일정 기간 동안 개인정보를 보관하였다가 삭제합니다.
                </p>
                <p>
                  회원 가입, 서비스 이용 관련 정보 (6개월 보관)<br>
                  탈퇴 회원 관련 정보 (6개월 보관)
                </p>
                법에서 정하고 있는 경우
                <p>
                  법에서 정해둔 기간에 따라 보관하는 개인정보는 아래와 같습니다. 
                  다른 이유로는 절대 이용하지 않으며 보관해야 하는 기간이 지나면 빠르게 삭제합니다.
                </p>
                <p>
                  &lt;관련법&gt;
                  전자상거래 등에서 소비자 보호에 관한 법률
                  계약 또는 청약철회 등에 관한 기록 (5년)
                  : 계약을 하거나 계약을 취소했던 기록 등
                  대금결제 및 재화 등의 공급에 관한 기록 (5년)
                  : 비용을 지불하거나 판매 물건 등을 제공했던 기록 등
                  소비자의 불만 또는 분쟁 처리에 관한 기록 (3년)
                  : 이용자의 문의나 요청, 다툼 해결에 관한 기록 등
                  전자금융거래법
                  전자금융에 관한 기록 (5년)
                  : 인터넷을 이용하여 돈을 주고 받은 기록 등
                  통신비밀보호법
                  로그인 기록 (3개월)
                  : 서비스에 방문하여 아이디와 비밀번호를 입력한 기록 등
                </p>
              </v-card-text>
            </v-card>
            <v-checkbox
              label="해당 내용에 동의하였고 회원가입을 진행합니다"
              :rules="[rules.required]"
            />
            <v-btn class="ma-1 primary base" width="200px" large @click="step1(false)">선생님입니다</v-btn>
            <v-btn class="ma-1 primary base" width="200px" large @click="step1(true)">학생입니다</v-btn>
          </v-form>
        </v-stepper-content>
        <v-stepper-content step="2">

          <v-form
            ref="step2"
            class="my-1"
            lazy-validation
          >

            <v-text-field
              ref="id"
              v-model="user.user_id"
              :success="success"
              :error="error"
              :success-messages="successMsg"
              :error-messages="errorMsg"
              :rules="[rules.required, rules.length, rules.eng]"
              :loading="isLoading"
              label="아이디"
              counter
              maxlength="15"
              outlined
              @input="validation"
            />

            <v-text-field
              v-model="user.pw"
              :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.required, rules.length]"
              :type="showPassword ? 'text' : 'password'"
              label="비밀번호"
              maxlength="30"
              outlined
              @click:append="showPassword = !showPassword"
            />

            <v-text-field
              v-model="user.kor_nm"
              :rules="[rules.required, rules.kor]"
              :validate-on-blur="false"
              label="이름"
              maxlength="10"
              outlined
            />

            <v-row no-gutters>
              <v-col>
                <v-select
                  v-model="user.grade"
                  :items="[1, 2, 3, 4, 5, 6]"
                  :rules="[rules.required]"
                  :validate-on-blur="false"
                  class="mr-1"
                  label="학년"
                  outlined
                />
              </v-col>
              <v-col>
                <v-text-field
                  v-model="user.ban"
                  :rules="[rules.required, rules.eng]"
                  :validate-on-blur="false"
                  label="반"
                  maxlength="3"
                  outlined
                />
              </v-col>
              <v-col v-if="isStudent">
                <v-text-field
                  v-model="user.bunho"
                  :rules="[rules.required, rules.eng]"
                  :validate-on-blur="false"
                  class="ml-1"
                  label="번호"
                  maxlength="3"
                  outlined
                />
              </v-col>
            </v-row>

            <v-select
              v-if="isStudent"
              v-model="user.gender_cd"
              :items="[
                { text: '남', value: '002001'}, 
                { text: '여', value: '002002'}, 
              ]"
              :rules="[rules.required]"
              :validate-on-blur="false"
              label="성별"
              outlined
            />

            <v-menu
              v-if="isStudent"
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="user.birth_dt"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="user.birth_dt"
                  label="생년월일"
                  prepend-icon="mdi-calendar"
                  readonly
                  :rules="[rules.required]"
                  v-bind="attrs"
                  v-on="on"
                />
              </template>
              <v-date-picker
                ref="picker"
                v-model="user.birth_dt"
                scrollable
                locale="ko-kr"
              >
                <v-spacer/>
                <v-btn
                  text
                  color="primary"
                  @click="menu = false"
                >
                  Cancel
                </v-btn>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.menu.save(user.birth_dt)"
                >
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>

            <v-text-field
              v-if="!isStudent"
              v-model="user.email"
              :rules="[rules.required, rules.email]"
              :validate-on-blur="false"
              label="이메일"
              maxlength="50"
              outlined
            />

            <v-btn
              :loading="isLoading"
              :disabled="isSignUpDisabled"
              class="ma-1"
              width="200px"
              x-large
              color="primary"
              @click="e1--"
            >뒤로</v-btn>

            <v-btn
              :loading="isLoading"
              :disabled="isSignUpDisabled"
              class="ma-1"
              width="200px"
              x-large
              color="primary"
              @click="step2"
            >생성</v-btn>

          </v-form>

        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
    
    <div class="text-center mt-6">
      아이디가 이미 있으신가요?
      <router-link to="/auth/signin" class="font-weight-bold">
        로그인
      </router-link>
    </div>
  </div>
</template>

<script>
/*
|---------------------------------------------------------------------
| 회원가입 Component
|---------------------------------------------------------------------
*/
import axios from 'axios'
import _ from 'lodash'

export default {
  data() {
    return {
      e1: 1,
      // sign up buttons
      isLoading: false,
      isSignUpDisabled: false,
      isStudent: false,
      timer: null,

      // form
      user: {
        user_id: '',
        pw: '',
        kor_nm: '',
        grade: null,
        ban: null,
        bunho: null,
        school_cd: '',
        email: '',
        gender_cd: '',
        birth_dt: ''
      },

      menu: false,

      // id field
      error: false,
      errorMsg: '',
      success: false,
      successMsg: '',

      // show password field
      showPassword: false,

      // input rules
      rules: {
        required: (value) => !!value || '필수 항목입니다',
        length: (value) => value.length >= 5 || '최소 5자이상 입력하세요',
        eng: (value) => /^[a-z0-9]+$/.test(value) || '영문 소문자와 숫자만 입력해 주세요',
        kor: (value) => /^[가-힣]+$/.test(value) || '한글만 입력해 주세요',
        email: (value) => /^\w+@\w+\.\w+(.\w+)*$/.test(value) || '이메일 형식에 맞게 입력해 주세요'
      }
    }
  },
  computed: {
    schoolList() {
      // TODO: 서버에서 001코드 내려받아 사용
      return [
        { text: '남산초등학교', value: '001002' }, 
        { text: '테스트', value: '001001' }
      ]
    }
  },
  watch: {
    menu (val) {
      val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
    }
  },
  methods: {
    step1(student) {
      if (this.$refs.step1.validate()) {
        this.isStudent = student
        this.e1++
      }
    },
    step2() {
      if (this.$refs.step2.validate()) {
        this.isLoading = true
        this.isSignUpDisabled = true
        this.signUp()
      }
    },
    signUp() {

      axios.post('/api/common/signup', { 
        ...this.user, 
        position_cd: this.isStudent ? '003003' : '003002'
      })
        .then((res) => {
          this.$router.push('/auth/signin')
        })
        .catch(() => {
          this.isLoading = false
        })

    },
    validation(value) {
      this.reset()

      if (this.$refs.id.validate()) {
        if (this.timer) {
          clearTimeout(this.timer)
        }

        this.timer = setTimeout(() => {
          this.isLoading = true
          axios.get(`/api/common/check?user_id=${value}`)
            .then((res) => {
              if (!res.data) {
                this.success = true
                this.successMsg = '아이디 생성이 가능합니다.'
              } else {
                this.error = true
                this.errorMsg = '중복아이디입니다.'
              }
            })
            .catch((err) => console.log(err))
            .then(() => this.isLoading = false)
        }, 500)
        
      }
    },
    reset() {
      this.success = false
      this.successMsg = ''
      this.error = false
      this.errorMsg = ''
    }
  }
}
</script>
