import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  StyleSheet,
  FlatList,
  TouchableOpacity,
  Image,
  Alert,
  Modal,
  TextInput,
} from 'react-native';
import LinearGradient from 'react-native-linear-gradient';
import Icon from 'react-native-vector-icons/MaterialIcons';
import { Moment } from '../../types';

const MomentsScreen: React.FC = () => {
  const [activeTab, setActiveTab] = useState<'private_circle' | 'public_sea'>('private_circle');
  const [moments, setMoments] = useState<Moment[]>([]);
  const [showCreateModal, setShowCreateModal] = useState(false);
  const [newMomentContent, setNewMomentContent] = useState('');
  const [newMomentType, setNewMomentType] = useState<'normal' | 'anonymous_qa' | 'help_request'>('normal');

  useEffect(() => {
    loadMoments();
  }, [activeTab]);

  const loadMoments = () => {
    // 模拟动态数据
    const mockMoments: Moment[] = [
      {
        id: '1',
        userId: '87654321',
        user: {
          uid: '87654321',
          nickname: '星辰小仙女',
          avatar: '',
          signature: '愿所有美好都如期而至 ✨',
          birthday: '1998-05-20',
          constellation: '金牛座',
          interests: ['阅读', '旅行'],
          education: {
            level: '硕士',
            school: '清华大学',
            major: '计算机科学',
            verified: true,
          },
          work: {
            company: '字节跳动',
            position: '产品经理',
            verified: true,
          },
          personalInfo: {
            height: 165,
            hometown: '北京',
            family: '独生女',
            lifestyle: '规律作息',
            idealPartner: '幽默有趣',
          },
          photos: [],
          starPower: 85,
          isVerified: true,
          createdAt: '2024-01-15',
          lastActive: '2024-01-20',
          phone: '13800138000',
        },
        content: '今天在图书馆看到了一本很有趣的书《星座与人格》，感觉对理解自己和他人都很有帮助。分享给大家～ 📚✨',
        images: [],
        type: 'normal',
        visibility: activeTab,
        tags: ['读书', '成长'],
        likes: ['76543210', '65432109'],
        comments: [
          {
            id: '1',
            userId: '76543210',
            user: {
              uid: '76543210',
              nickname: '追光少年',
              avatar: '',
              signature: '生活不止眼前的苟且',
              birthday: '1996-08-15',
              constellation: '狮子座',
              interests: ['健身', '电影'],
              education: {
                level: '本科',
                school: '北京大学',
                major: '软件工程',
                verified: true,
              },
              work: {
                company: '腾讯',
                position: '软件工程师',
                verified: true,
              },
              personalInfo: {
                height: 178,
                hometown: '上海',
                family: '家庭和睦',
                lifestyle: '热爱运动',
                idealPartner: '善良温柔',
              },
              photos: [],
              starPower: 78,
              isVerified: true,
              createdAt: '2024-01-10',
              lastActive: '2024-01-20',
              phone: '13900139000',
            },
            content: '这本书我也看过，确实很不错！',
            createdAt: '2024-01-20T11:00:00Z',
          }
        ],
        createdAt: '2024-01-20T10:30:00Z',
      },
      {
        id: '2',
        userId: 'anonymous',
        user: {
          uid: 'anonymous',
          nickname: '匿名用户',
          avatar: '',
          signature: '',
          birthday: '',
          constellation: '',
          interests: [],
          education: {
            level: '',
            school: '',
            major: '',
            verified: false,
          },
          work: {
            company: '',
            position: '',
            verified: false,
          },
          personalInfo: {
            height: 0,
            hometown: '',
            family: '',
            lifestyle: '',
            idealPartner: '',
          },
          photos: [],
          starPower: 0,
          isVerified: false,
          createdAt: '',
          lastActive: '',
          phone: '',
        },
        content: '最近工作压力很大，有没有什么好的减压方法推荐？求助各位星友～ 🙏',
        images: [],
        type: 'help_request',
        visibility: activeTab,
        tags: ['求助', '压力'],
        likes: ['87654321'],
        comments: [],
        createdAt: '2024-01-19T20:15:00Z',
      },
    ];

    setMoments(mockMoments.filter(m => m.visibility === activeTab));
  };

  const formatTime = (timestamp: string) => {
    const date = new Date(timestamp);
    const now = new Date();
    const diffMs = now.getTime() - date.getTime();
    const diffHours = Math.floor(diffMs / (1000 * 60 * 60));
    const diffDays = Math.floor(diffHours / 24);

    if (diffHours < 1) return '刚刚';
    if (diffHours < 24) return `${diffHours}小时前`;
    if (diffDays < 7) return `${diffDays}天前`;
    return date.toLocaleDateString();
  };

  const handleLike = (momentId: string) => {
    setMoments(prevMoments =>
      prevMoments.map(moment => {
        if (moment.id === momentId) {
          const isLiked = moment.likes.includes('current_user_id');
          return {
            ...moment,
            likes: isLiked
              ? moment.likes.filter(id => id !== 'current_user_id')
              : [...moment.likes, 'current_user_id']
          };
        }
        return moment;
      })
    );
  };

  const handleComment = (momentId: string) => {
    Alert.alert('评论功能', '评论功能开发中');
  };

  const handleCreateMoment = () => {
    if (!newMomentContent.trim()) {
      Alert.alert('提示', '请输入动态内容');
      return;
    }

    const newMoment: Moment = {
      id: Date.now().toString(),
      userId: 'current_user_id',
      user: {
        uid: '12345678',
        nickname: '我',
        avatar: '',
        signature: '这是我的个性签名',
        birthday: '1995-06-15',
        constellation: '双子座',
        interests: ['编程', '音乐'],
        education: {
          level: '本科',
          school: '某某大学',
          major: '计算机科学',
          verified: true,
        },
        work: {
          company: '某某公司',
          position: '开发工程师',
          verified: true,
        },
        personalInfo: {
          height: 175,
          hometown: '北京',
          family: '独生子',
          lifestyle: '规律生活',
          idealPartner: '温柔善良',
        },
        photos: [],
        starPower: 88,
        isVerified: true,
        createdAt: '2024-01-01',
        lastActive: '2024-01-20',
        phone: '13700137000',
      },
      content: newMomentContent,
      images: [],
      type: newMomentType,
      visibility: activeTab,
      tags: [],
      likes: [],
      comments: [],
      createdAt: new Date().toISOString(),
    };

    setMoments(prevMoments => [newMoment, ...prevMoments]);
    setNewMomentContent('');
    setShowCreateModal(false);
    Alert.alert('发布成功', '动态已发布到' + (activeTab === 'private_circle' ? '私域星圈' : '公域星海'));
  };

  const renderMomentItem = ({ item }: { item: Moment }) => (
    <View style={styles.momentItem}>
      <LinearGradient
        colors={['#16213e', '#1a1a2e']}
        style={styles.momentGradient}
      >
        {/* 用户信息 */}
        <View style={styles.momentHeader}>
          <View style={styles.userInfo}>
            <View style={[
              styles.avatar,
              item.type === 'anonymous_qa' && styles.anonymousAvatar
            ]}>
              <Text style={styles.avatarText}>
                {item.type === 'anonymous_qa' ? '?' : 
                 item.type === 'help_request' ? '🆘' :
                 item.user.nickname.charAt(0)}
              </Text>
            </View>
            <View style={styles.userDetails}>
              <View style={styles.userNameRow}>
                <Text style={styles.userName}>
                  {item.type === 'anonymous_qa' ? '匿名提问' :
                   item.type === 'help_request' ? '求助' :
                   item.user.nickname}
                </Text>
                {item.user.isVerified && item.type === 'normal' && (
                  <Icon name="verified" size={14} color="#ffd700" />
                )}
              </View>
              <Text style={styles.timeText}>{formatTime(item.createdAt)}</Text>
            </View>
          </View>
          
          {item.type !== 'anonymous_qa' && item.type !== 'help_request' && (
            <TouchableOpacity style={styles.moreButton}>
              <Icon name="more-horiz" size={20} color="#8892b0" />
            </TouchableOpacity>
          )}
        </View>

        {/* 动态内容 */}
        <View style={styles.momentContent}>
          <Text style={styles.contentText}>{item.content}</Text>
          
          {/* 标签 */}
          {item.tags.length > 0 && (
            <View style={styles.tagsContainer}>
              {item.tags.map((tag, index) => (
                <View key={index} style={styles.tag}>
                  <Text style={styles.tagText}>#{tag}</Text>
                </View>
              ))}
            </View>
          )}
        </View>

        {/* 互动区域 */}
        <View style={styles.interactionArea}>
          <TouchableOpacity
            style={styles.interactionButton}
            onPress={() => handleLike(item.id)}
          >
            <Icon
              name={item.likes.includes('current_user_id') ? 'favorite' : 'favorite-border'}
              size={18}
              color={item.likes.includes('current_user_id') ? '#ff6b6b' : '#8892b0'}
            />
            <Text style={styles.interactionText}>{item.likes.length}</Text>
          </TouchableOpacity>

          <TouchableOpacity
            style={styles.interactionButton}
            onPress={() => handleComment(item.id)}
          >
            <Icon name="chat-bubble-outline" size={18} color="#8892b0" />
            <Text style={styles.interactionText}>{item.comments.length}</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.interactionButton}>
            <Icon name="share" size={18} color="#8892b0" />
            <Text style={styles.interactionText}>分享</Text>
          </TouchableOpacity>
        </View>

        {/* 评论区域 */}
        {item.comments.length > 0 && (
          <View style={styles.commentsSection}>
            {item.comments.slice(0, 2).map((comment) => (
              <View key={comment.id} style={styles.commentItem}>
                <Text style={styles.commentUser}>{comment.user.nickname}:</Text>
                <Text style={styles.commentContent}>{comment.content}</Text>
              </View>
            ))}
            {item.comments.length > 2 && (
              <TouchableOpacity style={styles.viewMoreComments}>
                <Text style={styles.viewMoreText}>查看更多评论</Text>
              </TouchableOpacity>
            )}
          </View>
        )}
      </LinearGradient>
    </View>
  );

  return (
    <LinearGradient colors={['#0f0f23', '#1a1a2e']} style={styles.container}>
      {/* 头部 */}
      <View style={styles.header}>
        <Text style={styles.title}>动态</Text>
        <TouchableOpacity
          style={styles.createButton}
          onPress={() => setShowCreateModal(true)}
        >
          <Icon name="add" size={24} color="#ffd700" />
        </TouchableOpacity>
      </View>

      {/* 标签页 */}
      <View style={styles.tabContainer}>
        <TouchableOpacity
          style={[styles.tab, activeTab === 'private_circle' && styles.activeTab]}
          onPress={() => setActiveTab('private_circle')}
        >
          <Text style={[styles.tabText, activeTab === 'private_circle' && styles.activeTabText]}>
            私域星圈
          </Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={[styles.tab, activeTab === 'public_sea' && styles.activeTab]}
          onPress={() => setActiveTab('public_sea')}
        >
          <Text style={[styles.tabText, activeTab === 'public_sea' && styles.activeTabText]}>
            公域星海
          </Text>
        </TouchableOpacity>
      </View>

      {/* 动态列表 */}
      <FlatList
        data={moments}
        keyExtractor={(item) => item.id}
        renderItem={renderMomentItem}
        contentContainerStyle={styles.listContainer}
        showsVerticalScrollIndicator={false}
        ListEmptyComponent={
          <View style={styles.emptyContainer}>
            <Text style={styles.emptyText}>暂无动态</Text>
            <Text style={styles.emptySubtext}>
              {activeTab === 'private_circle' ? '和好友分享你的生活吧！' : '在公域星海发现更多精彩！'}
            </Text>
          </View>
        }
      />

      {/* 创建动态模态框 */}
      <Modal
        visible={showCreateModal}
        animationType="slide"
        transparent={true}
        onRequestClose={() => setShowCreateModal(false)}
      >
        <View style={styles.modalOverlay}>
          <LinearGradient
            colors={['#16213e', '#1a1a2e']}
            style={styles.modalContent}
          >
            <View style={styles.modalHeader}>
              <TouchableOpacity onPress={() => setShowCreateModal(false)}>
                <Text style={styles.modalCancel}>取消</Text>
              </TouchableOpacity>
              <Text style={styles.modalTitle}>发布动态</Text>
              <TouchableOpacity onPress={handleCreateMoment}>
                <Text style={styles.modalConfirm}>发布</Text>
              </TouchableOpacity>
            </View>

            <View style={styles.modalBody}>
              <TextInput
                style={styles.contentInput}
                placeholder="分享你的想法..."
                placeholderTextColor="#8892b0"
                value={newMomentContent}
                onChangeText={setNewMomentContent}
                multiline
                maxLength={500}
              />

              <View style={styles.typeSelector}>
                <Text style={styles.typeSelectorTitle}>发布类型:</Text>
                <View style={styles.typeOptions}>
                  <TouchableOpacity
                    style={[styles.typeOption, newMomentType === 'normal' && styles.activeTypeOption]}
                    onPress={() => setNewMomentType('normal')}
                  >
                    <Text style={[styles.typeOptionText, newMomentType === 'normal' && styles.activeTypeOptionText]}>
                      普通动态
                    </Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                    style={[styles.typeOption, newMomentType === 'anonymous_qa' && styles.activeTypeOption]}
                    onPress={() => setNewMomentType('anonymous_qa')}
                  >
                    <Text style={[styles.typeOptionText, newMomentType === 'anonymous_qa' && styles.activeTypeOptionText]}>
                      匿名问答
                    </Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                    style={[styles.typeOption, newMomentType === 'help_request' && styles.activeTypeOption]}
                    onPress={() => setNewMomentType('help_request')}
                  >
                    <Text style={[styles.typeOptionText, newMomentType === 'help_request' && styles.activeTypeOptionText]}>
                      求助
                    </Text>
                  </TouchableOpacity>
                </View>
              </View>

              <View style={styles.visibilityInfo}>
                <Text style={styles.visibilityText}>
                  将发布到: {activeTab === 'private_circle' ? '私域星圈' : '公域星海'}
                </Text>
              </View>
            </View>
          </LinearGradient>
        </View>
      </Modal>
    </LinearGradient>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingHorizontal: 20,
    paddingTop: 50,
    paddingBottom: 20,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#ccd6f6',
  },
  createButton: {
    width: 40,
    height: 40,
    borderRadius: 20,
    backgroundColor: '#16213e',
    justifyContent: 'center',
    alignItems: 'center',
  },
  tabContainer: {
    flexDirection: 'row',
    paddingHorizontal: 20,
    marginBottom: 20,
  },
  tab: {
    flex: 1,
    paddingVertical: 12,
    alignItems: 'center',
    borderRadius: 20,
    marginHorizontal: 4,
  },
  activeTab: {
    backgroundColor: '#ffd700',
  },
  tabText: {
    fontSize: 14,
    color: '#8892b0',
    fontWeight: '500',
  },
  activeTabText: {
    color: '#1a1a2e',
    fontWeight: 'bold',
  },
  listContainer: {
    paddingHorizontal: 20,
    paddingBottom: 20,
  },
  momentItem: {
    marginBottom: 16,
    borderRadius: 12,
    overflow: 'hidden',
  },
  momentGradient: {
    padding: 16,
  },
  momentHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 12,
  },
  userInfo: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  avatar: {
    width: 40,
    height: 40,
    borderRadius: 20,
    backgroundColor: '#ffd700',
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 12,
  },
  anonymousAvatar: {
    backgroundColor: '#8892b0',
  },
  avatarText: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#1a1a2e',
  },
  userDetails: {
    flex: 1,
  },
  userNameRow: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  userName: {
    fontSize: 14,
    fontWeight: 'bold',
    color: '#ccd6f6',
    marginRight: 4,
  },
  timeText: {
    fontSize: 12,
    color: '#8892b0',
    marginTop: 2,
  },
  moreButton: {
    padding: 4,
  },
  momentContent: {
    marginBottom: 12,
  },
  contentText: {
    fontSize: 14,
    color: '#ccd6f6',
    lineHeight: 20,
    marginBottom: 8,
  },
  tagsContainer: {
    flexDirection: 'row',
    flexWrap: 'wrap',
  },
  tag: {
    backgroundColor: '#ffd700',
    paddingHorizontal: 8,
    paddingVertical: 4,
    borderRadius: 10,
    marginRight: 6,
    marginBottom: 4,
  },
  tagText: {
    fontSize: 10,
    color: '#1a1a2e',
    fontWeight: '600',
  },
  interactionArea: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    paddingVertical: 8,
    borderTopWidth: 1,
    borderTopColor: '#2a2a3e',
  },
  interactionButton: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 4,
    paddingHorizontal: 8,
  },
  interactionText: {
    fontSize: 12,
    color: '#8892b0',
    marginLeft: 4,
  },
  commentsSection: {
    marginTop: 8,
    paddingTop: 8,
    borderTopWidth: 1,
    borderTopColor: '#2a2a3e',
  },
  commentItem: {
    flexDirection: 'row',
    marginBottom: 4,
  },
  commentUser: {
    fontSize: 12,
    color: '#ffd700',
    fontWeight: '600',
    marginRight: 4,
  },
  commentContent: {
    fontSize: 12,
    color: '#ccd6f6',
    flex: 1,
  },
  viewMoreComments: {
    marginTop: 4,
  },
  viewMoreText: {
    fontSize: 12,
    color: '#8892b0',
  },
  emptyContainer: {
    alignItems: 'center',
    justifyContent: 'center',
    paddingVertical: 60,
  },
  emptyText: {
    fontSize: 18,
    color: '#ccd6f6',
    marginBottom: 8,
  },
  emptySubtext: {
    fontSize: 14,
    color: '#8892b0',
    textAlign: 'center',
  },
  modalOverlay: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.7)',
    justifyContent: 'flex-end',
  },
  modalContent: {
    borderTopLeftRadius: 20,
    borderTopRightRadius: 20,
    minHeight: '60%',
  },
  modalHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: 20,
    borderBottomWidth: 1,
    borderBottomColor: '#2a2a3e',
  },
  modalCancel: {
    fontSize: 16,
    color: '#8892b0',
  },
  modalTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#ccd6f6',
  },
  modalConfirm: {
    fontSize: 16,
    color: '#ffd700',
    fontWeight: '600',
  },
  modalBody: {
    flex: 1,
    padding: 20,
  },
  contentInput: {
    fontSize: 16,
    color: '#ccd6f6',
    textAlignVertical: 'top',
    minHeight: 120,
    marginBottom: 20,
  },
  typeSelector: {
    marginBottom: 20,
  },
  typeSelectorTitle: {
    fontSize: 14,
    color: '#ccd6f6',
    marginBottom: 10,
    fontWeight: '600',
  },
  typeOptions: {
    flexDirection: 'row',
    flexWrap: 'wrap',
  },
  typeOption: {
    paddingHorizontal: 12,
    paddingVertical: 8,
    borderRadius: 15,
    backgroundColor: '#2a2a3e',
    marginRight: 8,
    marginBottom: 8,
  },
  activeTypeOption: {
    backgroundColor: '#ffd700',
  },
  typeOptionText: {
    fontSize: 12,
    color: '#8892b0',
    fontWeight: '500',
  },
  activeTypeOptionText: {
    color: '#1a1a2e',
    fontWeight: 'bold',
  },
  visibilityInfo: {
    marginTop: 'auto',
    paddingTop: 20,
  },
  visibilityText: {
    fontSize: 12,
    color: '#8892b0',
    textAlign: 'center',
  },
});

export default MomentsScreen;