FROM node:18-alpine as build

RUN npm install -g pnpm

WORKDIR /app

COPY package.json ./
COPY pnpm-lock.yaml ./

RUN pnpm install

COPY . .

ARG VITE_ENV
ENV VITE_ENV=${VITE_ENV}

RUN pnpm build

######

FROM nginx:stable-alpine

RUN rm -rf /etc/nginx/conf.d/default.conf

RUN rm -rf /usr/share/nginx/html/*

COPY --from=build /app/dist /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]
